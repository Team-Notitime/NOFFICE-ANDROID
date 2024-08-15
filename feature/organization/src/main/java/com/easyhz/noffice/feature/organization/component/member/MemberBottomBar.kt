package com.easyhz.noffice.feature.organization.component.member

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.component.button.MediumButton
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.feature.organization.util.member.ButtonStyle
import com.easyhz.noffice.feature.organization.util.member.MemberViewType


@Composable
internal fun MemberBottomBar(
    modifier: Modifier = Modifier,
    memberViewType: MemberViewType,
    onClickListener: MemberViewType.ClickListener
) {
    AnimatedContent(
        modifier = modifier.screenHorizonPadding(),
        targetState = memberViewType,
        label = "bottomBar"
    ) {
        MemberBottomBar(
            left = it.left,
            right = it.right,
            onClickListener = onClickListener
        )
    }
}

@Composable
private fun MemberBottomBar(
    modifier: Modifier = Modifier,
    left: ButtonStyle,
    right: ButtonStyle,
    onClickListener: MemberViewType.ClickListener
) {
    Row(
        modifier = modifier
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        MediumButton(
            modifier = Modifier.weight(1f),
            text = stringResource(id = left.title),
            contentColor = left.contentColor,
            containerColor = left.containerColor
        ) {
            onClickListener.onClickLeftButton()
        }
        MediumButton(
            modifier = Modifier.weight(1f),
            text = stringResource(id = right.title),
            contentColor = right.contentColor,
            containerColor = right.containerColor
        ) {
            onClickListener.onClickRightButton()
        }
    }
}