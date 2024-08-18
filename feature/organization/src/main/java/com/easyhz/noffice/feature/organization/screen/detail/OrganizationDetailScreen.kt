package com.easyhz.noffice.feature.organization.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.easyhz.noffice.core.common.util.collectInSideEffectWithLifecycle
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.divider.divider
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeBasicScaffold
import com.easyhz.noffice.core.design_system.component.topBar.DetailTopBar
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.theme.Grey50
import com.easyhz.noffice.core.design_system.theme.Grey600
import com.easyhz.noffice.core.design_system.theme.SemiBold16
import com.easyhz.noffice.core.design_system.util.topBar.DetailTopBarMenu
import com.easyhz.noffice.core.model.organization.OrganizationInformation
import com.easyhz.noffice.feature.organization.component.detail.AnnouncementCard
import com.easyhz.noffice.feature.organization.component.detail.DetailHeader
import com.easyhz.noffice.feature.organization.component.detail.NumberOfMembersView
import com.easyhz.noffice.feature.organization.component.detail.SkeletonCard
import com.easyhz.noffice.feature.organization.component.detail.StandbyMemberButton
import com.easyhz.noffice.feature.organization.contract.detail.DetailIntent
import com.easyhz.noffice.feature.organization.contract.detail.DetailSideEffect

@Composable
fun OrganizationDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: OrganizationDetailViewModel = hiltViewModel(),
    organizationId: Int,
    organizationName: String,
    snackBarHostState: SnackbarHostState,
    navigateToUp: () -> Unit,
    navigateToAnnouncementDetail: (Int, String) -> Unit,
    navigateToStandbyMember: (Int) -> Unit,
    navigateToOrganizationManagement: (OrganizationInformation) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val announcementList = viewModel.announcementState.collectAsLazyPagingItems()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.postIntent(DetailIntent.InitScreen(organizationId, organizationName))
    }
    NofficeBasicScaffold(
        containerColor = Grey50,
        statusBarColor = Grey50,
        navigationBarColor = Grey50,
        topBar = {
            DetailTopBar(
                modifier = Modifier.background(Grey50),
                leadingItem = DetailTopBarMenu(
                    content = {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.ic_chevron_left),
                            contentDescription = "left",
                            tint = Grey400
                        )
                    },
                    onClick = { viewModel.postIntent(DetailIntent.NavigateToUp) }
                ),
                trailingItem = DetailTopBarMenu(
                    content = {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.ic_edit),
                            contentDescription = "edit",
                            tint = Grey400
                        )
                    },
                    onClick = {
                        viewModel.postIntent(DetailIntent.ClickEditButton)
                    }
                ),
            )
        }
    ) { paddingValues ->
        if (!uiState.isCardLoading && announcementList.loadState.refresh != LoadState.Loading && announcementList.itemCount == 0) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = stringResource(id = R.string.organization_detail_announcement_empty),
                    style = SemiBold16,
                    color = Grey600
                )
            }
        }
        LazyColumn(
            modifier = modifier
                .padding(paddingValues)
                .screenHorizonPadding(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                DetailHeader(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
                    organizationInformation = uiState.organizationInformation,
                    isLoading = uiState.isLoading
                )
            }
            divider()
            item {
                NumberOfMembersView(
                    modifier = Modifier.padding(vertical = 8.dp),
                    numberOfMembers = uiState.organizationInformation.members,
                    isLoading = uiState.isLoading
                )
            }
            item {
                StandbyMemberButton(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    hasStandbyMember = uiState.organizationInformation.hasStandbyMember
                ) {
                    viewModel.postIntent(DetailIntent.ClickStandbyMemberButton)
                }
            }
            item {
                if (uiState.isCardLoading || announcementList.loadState.refresh == LoadState.Loading) {
                    repeat(3) {
                        SkeletonCard(modifier = Modifier.padding(vertical = 6.dp))
                    }
                }
            }
            items(announcementList.itemCount, key = { it }) { index ->
                announcementList[index]?.let { item ->
                    AnnouncementCard(
                        modifier = Modifier.animateItem(),
                        announcement = item,
                    ) {
                        viewModel.postIntent(
                            DetailIntent.ClickAnnouncement(
                                item.announcementId,
                                item.title
                            )
                        )
                    }
                }
            }
            item {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(12.dp)
                )
            }
        }
    }

    viewModel.sideEffect.collectInSideEffectWithLifecycle { sideEffect ->
        when (sideEffect) {
            is DetailSideEffect.NavigateToUp -> {
                navigateToUp()
            }

            is DetailSideEffect.NavigateToAnnouncementDetail -> {
                navigateToAnnouncementDetail(sideEffect.id, sideEffect.title)
            }

            is DetailSideEffect.NavigateToOrganizationManagement -> {
                navigateToOrganizationManagement(sideEffect.information)
            }

            is DetailSideEffect.NavigateToStandbyMember -> {
                navigateToStandbyMember(sideEffect.id)
            }

            is DetailSideEffect.ShowSnackBar -> {
                snackBarHostState.showSnackbar(
                    message = context.getString(sideEffect.stringId),
                    withDismissAction = true
                )
            }
        }
    }
}