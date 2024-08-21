package com.easyhz.noffice.feature.announcement.component.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.component.member.MemberItem
import com.easyhz.noffice.core.design_system.component.tab.SegmentedButton
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.model.organization.member.MemberType
import com.easyhz.noffice.feature.announcement.util.detail.InspectionType

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun InspectionBottomSheet(
    modifier: Modifier = Modifier,
    selectedInspectionType: InspectionType,
    onClickSegmentedButton: (InspectionType) -> Unit
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp
    LazyColumn(
        modifier = modifier.padding(top = 16.dp).screenHorizonPadding().heightIn(max = (screenHeight * 0.9).dp)
    ) {
        stickyHeader {
            SegmentedButton(
                selectedIndex = InspectionType.entries.indexOf(selectedInspectionType),
                items = enumValues<InspectionType>()
            ) {
                onClickSegmentedButton(it)
            }
        }

        items(20) {
            MemberItem(
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
                name = "멤버${it + 1}",
                imageUrl = if (it % 3 != 0) "" else "https://picsum.photos/id/${30 + it}/200/300",
                memberType = MemberType.PARTICIPANT,
                isChecked = null
            )
        }
    }
}