package com.easyhz.noffice.feature.home.component.notice

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.banner.Banner
import com.easyhz.noffice.core.design_system.component.banner.SkeletonBanner
import com.easyhz.noffice.core.design_system.component.card.ItemCard
import com.easyhz.noffice.core.design_system.component.card.SkeletonItemCard
import com.easyhz.noffice.core.design_system.component.skeleton.SkeletonProvider
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.util.card.CardDetailInfo
import com.easyhz.noffice.core.design_system.util.card.CardExceptionType
import com.easyhz.noffice.core.model.organization.JoinStatus
import com.easyhz.noffice.core.model.organization.Organization
import com.easyhz.noffice.feature.home.component.common.OrganizationHeader
import com.easyhz.noffice.feature.home.component.viewmodel.NoticeViewModel

@Composable
fun NoticeView(
    modifier: Modifier = Modifier,
    name: String,
    dayOfWeek: String,
    organizationList: LazyPagingItems<Organization>,
    isLoading: Boolean,
    isRefreshing: Boolean,
    onClickOrganizationHeader: (Organization) -> Unit,
    onClickAnnouncementCard: (organizationId: Int, announcementId: Int, announcementTitle: String) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(bottom = 48.dp)
    ) {
        item {
            SkeletonProvider(isLoading = isLoading, skeletonContent = { SkeletonBanner() }) {
                Banner(userName = name, date = dayOfWeek)
            }
        }
        if (isLoading && !organizationList.loadState.hasError) {
            item {
                SkeletonOrganizationSection()
            }
        }
        items(organizationList.itemCount) { index ->
            organizationList[index]?.let {
                OrganizationSection(
                    organization = it,
                    isRefreshing = isRefreshing,
                    onClickOrganizationHeader = { onClickOrganizationHeader(it) },
                    onClickItemCard = { id, title -> onClickAnnouncementCard(it.id, id, title) }
                )
            }
        }
    }
}

@Composable
private fun OrganizationSection(
    modifier: Modifier = Modifier,
    noticeViewModel: NoticeViewModel = hiltViewModel(),
    organization: Organization,
    isRefreshing: Boolean,
    onClickOrganizationHeader: () -> Unit,
    onClickItemCard: (announcementId: Int, announcementTitle: String) -> Unit,
) {
    val announcementList = noticeViewModel.getAnnouncementStateByOrganization(organizationId = organization.id).collectAsLazyPagingItems()
    val isRefreshingAnnouncement = announcementList.loadState.refresh == LoadState.Loading
    LaunchedEffect(organization.id) {
        noticeViewModel.fetchAnnouncementByOrganization(organization.id)
    }

    LaunchedEffect(key1 = isRefreshing) {
        if(!isRefreshing) return@LaunchedEffect
        noticeViewModel.refreshAnnouncementByOrganization(organization.id)
    }

    Column(modifier) {
        OrganizationHeader(
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .screenHorizonPadding()
                .padding(vertical = 8.dp),
            organizationName = organization.name
        ) { onClickOrganizationHeader() }
        LazyRow(
            userScrollEnabled = (organization.joinStatus != JoinStatus.PENDING) && (announcementList.itemCount != 0),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
        ) {
            when(organization.joinStatus) {
                JoinStatus.ACTIVE -> {
                    items(announcementList.itemCount, key = { announcementList[it]?.announcementId ?: -1 }) {index ->
                        announcementList[index]?.let {
                            ItemCard(
                                title = it.title,
                                imageUrl = it.profileImage ?: "",
                                detailItems = listOf(
                                    CardDetailInfo(
                                        iconId = R.drawable.ic_calendar,
                                        text = it.endAt ?: stringResource(id = R.string.home_date_empty),
                                        description = "date"
                                    ),
                                    CardDetailInfo(
                                        iconId = R.drawable.ic_mappin,
                                        text = it.place ?: stringResource(id = R.string.home_place_empty),
                                        description = "place"
                                    )
                                )
                            ) {
                                onClickItemCard(it.announcementId, it.title)
                            }
                        }
                    }
                    if (announcementList.itemCount == 0 && !isRefreshingAnnouncement) {
                        exceptionItem(CardExceptionType.NO_RESULT)
                    } else if (announcementList.itemCount == 0){
                        items(2) {
                            SkeletonItemCard()
                        }
                    }
                }
                JoinStatus.PENDING -> {
                    exceptionItem(CardExceptionType.ACCEPT_WAIT)
                }
                else -> { }
            }
        }
    }
}