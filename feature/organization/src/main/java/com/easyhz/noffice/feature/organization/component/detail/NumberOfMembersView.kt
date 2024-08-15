package com.easyhz.noffice.feature.organization.component.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.common.util.NumberFormat.displayNumber
import com.easyhz.noffice.core.design_system.component.skeleton.SkeletonProvider
import com.easyhz.noffice.core.design_system.extension.skeletonEffect
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.theme.SubTitle1
import com.easyhz.noffice.core.model.organization.member.MemberType

@Composable
internal fun NumberOfMembersView(
    modifier: Modifier = Modifier,
    numberOfMembers: LinkedHashMap<MemberType, Int>,
    isLoading: Boolean
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        numberOfMembers.forEach { (key, value) ->
            MemberItem(key = key, value = value, isLoading = isLoading)
        }
    }
}

@Composable
private fun MemberItem(
    modifier: Modifier = Modifier,
    key: MemberType,
    value: Int,
    isLoading: Boolean,
) {
    SkeletonProvider(isLoading = isLoading, skeletonContent = {
        Box(
            modifier = Modifier
                .width(100.dp)
                .height(20.dp)
                .skeletonEffect()
        )
    }) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = stringResource(id = key.stringId),
                style = SubTitle1,
                color = Grey400
            )
            Text(
                text = displayNumber(value),
                style = SubTitle1
            )
        }
    }
}