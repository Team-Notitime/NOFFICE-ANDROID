package com.easyhz.noffice.feature.organization.screen.organization

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.easyhz.noffice.core.common.util.collectInSideEffectWithLifecycle
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.button.IconMediumButton
import com.easyhz.noffice.core.design_system.component.exception.ExceptionView
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeScaffold
import com.easyhz.noffice.core.design_system.component.topBar.HomeTopBar
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Green500
import com.easyhz.noffice.core.design_system.util.exception.ExceptionType
import com.easyhz.noffice.feature.organization.component.organization.OrganizationItem
import com.easyhz.noffice.feature.organization.component.organization.SkeletonOrganizationItem
import com.easyhz.noffice.feature.organization.contract.organization.OrganizationIntent
import com.easyhz.noffice.feature.organization.contract.organization.OrganizationSideEffect
import com.easyhz.noffice.feature.organization.util.OrganizationTopBarMenu

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OrganizationScreen(
    modifier: Modifier = Modifier,
    viewModel: OrganizationViewModel = hiltViewModel(),
    navigateToMyPage: () -> Unit,
    navigateToDetail: (Int, String) -> Unit,
    navigateToCreation: () -> Unit,
    navigateToNotification: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val organizationList = viewModel.organizationState.collectAsLazyPagingItems()
    val isRefreshing = remember(organizationList.loadState.refresh) {
        organizationList.loadState.refresh == LoadState.Loading
    }

    val pullRefreshState = rememberPullRefreshState(refreshing = isRefreshing, onRefresh = {
        viewModel.postIntent(OrganizationIntent.Refresh)
    })

    NofficeScaffold(
        topBar = {
            HomeTopBar(
                tabs = enumValues<OrganizationTopBarMenu>(),
                onClickIconMenu = { viewModel.postIntent(OrganizationIntent.ClickTopBarIconMenu(it)) }
            ) { }
        }
    ) { paddingValues ->
        if(organizationList.itemCount == 0 && organizationList.loadState.refresh != LoadState.Loading) {
            ExceptionView(
                modifier = Modifier.fillMaxSize(),
                type = ExceptionType.NO_ORGANIZATION
            )
        }
        Box(modifier = modifier.fillMaxSize().pullRefresh(pullRefreshState)) {
            Column(
                modifier = modifier
                    .padding(top = paddingValues.calculateTopPadding())
                    .screenHorizonPadding()
            ) {
                IconMediumButton(
                    modifier = Modifier.padding(vertical = 18.dp),
                    text = stringResource(id = R.string.organization_new),
                    iconId = R.drawable.ic_plus
                ) {
                    viewModel.postIntent(OrganizationIntent.ClickOrganizationCreation)
                }
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    item {
                        if (organizationList.loadState.refresh == LoadState.Loading) {
                            repeat(3) {
                                SkeletonOrganizationItem()
                            }
                        }
                    }
                    items(organizationList.itemCount, key = { organizationList[it]?.id ?: -1}) { index ->
                        organizationList[index]?.let { item ->
                            OrganizationItem(
                                modifier = Modifier.fillMaxWidth(),
                                organizationName = item.name,
                                imageUrl = item.profileImageUrl
                            ) {
                                viewModel.postIntent(
                                    OrganizationIntent.ClickOrganization(
                                        id = item.id,
                                        name = item.name
                                    )
                                )
                            }
                        }
                    }
                }
            }
            if (!uiState.isRefreshing) {
                PullRefreshIndicator(
                    refreshing = isRefreshing,
                    contentColor = Green500,
                    state = pullRefreshState,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 20.dp)
                )
            }
        }
    }

    viewModel.sideEffect.collectInSideEffectWithLifecycle {sideEffect ->
        when(sideEffect) {
            is OrganizationSideEffect.NavigateToCreation -> { navigateToCreation() }
            is OrganizationSideEffect.NavigateToDetail -> { navigateToDetail(sideEffect.id, sideEffect.name) }
            is OrganizationSideEffect.NavigateToMyPage -> { navigateToMyPage() }
            is OrganizationSideEffect.Refresh -> { organizationList.refresh() }
            is OrganizationSideEffect.NavigateToNotification -> { navigateToNotification() }
        }
    }
}