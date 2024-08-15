package com.easyhz.noffice.feature.home.component.notice

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.component.banner.Banner
import com.easyhz.noffice.core.design_system.component.card.ItemCard
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.util.card.CardDetailInfo
import com.easyhz.noffice.core.design_system.util.card.CardExceptionType
import com.easyhz.noffice.feature.home.component.common.OrganizationHeader

@Composable
fun NoticeView(
    modifier: Modifier = Modifier,
    navigateToAnnouncementDetail: (Int, String) -> Unit
) {
    Column(modifier = modifier) {
        Banner(userName = "푸바옹", date = "금요일")
        OrganizationHeader(
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .screenHorizonPadding()
                .padding(vertical = 8.dp),
            organizationName = "CMC 15th"
        ) {

        }
        LazyRow(
            userScrollEnabled = true,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
        ) {
            item {
                ItemCard(
                    title = "5차 세션 : 최종 팀 빌딩 ~ 제목이 두 줄 일 때",
                    detailItems = listOf(
                        CardDetailInfo(
                            iconId = R.drawable.ic_calendar,
                            text = "2024.06.29(토) 14:00",
                            description = "date"
                        ),
                        CardDetailInfo(
                            iconId = R.drawable.ic_mappin,
                            text = "ZEP",
                            description = "place"
                        )
                    )
                ) {
                    navigateToAnnouncementDetail(1, "5차 세션 : 최종 팀 빌딩 ~ 제목이 두 줄 일 때")
                }
            }
            exceptionItem(CardExceptionType.NO_RESULT)
            exceptionItem(CardExceptionType.ACCEPT_WAIT)
        }
    }
}