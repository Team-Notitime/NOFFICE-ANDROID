package com.easyhz.noffice.feature.announcement.component.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.member.MemberItem
import com.easyhz.noffice.core.design_system.component.tab.SegmentedButton
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Grey600
import com.easyhz.noffice.core.design_system.theme.SemiBold16
import com.easyhz.noffice.core.model.common.Member
import com.easyhz.noffice.core.model.organization.member.MemberType
import com.easyhz.noffice.feature.announcement.util.detail.ReaderType

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun ReaderBottomSheet(
    modifier: Modifier = Modifier,
    selectedReaderType: ReaderType,
    readerList: List<Member>,
    nonReaderList: List<Member>,
    onClickSegmentedButton: (ReaderType) -> Unit
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp
    val currentList =
        when (selectedReaderType) {
            ReaderType.READER -> readerList
            ReaderType.NON_READER -> nonReaderList
        }

    LazyColumn(
        modifier = modifier
            .padding(top = 16.dp)
            .screenHorizonPadding()
            .height((screenHeight * 0.9).dp)
    ) {
        stickyHeader {
            SegmentedButton(
                selectedIndex = ReaderType.entries.indexOf(selectedReaderType),
                items = enumValues<ReaderType>()
            ) {
                onClickSegmentedButton(it)
            }
        }

        if (currentList.isEmpty()) {
            item {
                Box(modifier = Modifier.fillMaxWidth().heightIn(min = 480.dp), contentAlignment = Alignment.Center) {
                    Text(
                        text = stringResource(id = selectedReaderType.emptyStringId) + " " +
                                stringResource(id = R.string.announcement_detail_common_reader_empty),
                        style = SemiBold16,
                        color = Grey600
                    )
                }
            }
        }
        items(currentList, key = { it.id }) {
            MemberItem(
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
                name = it.alias,
                imageUrl = it.profileImage,
                memberType = MemberType.PARTICIPANT,
                isChecked = null
            )
        }
    }
}