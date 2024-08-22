package com.easyhz.noffice.feature.home.screen.home

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.easyhz.noffice.core.common.manager.DeepLinkManager
import com.easyhz.noffice.core.common.util.collectInSideEffectWithLifecycle
import com.easyhz.noffice.core.design_system.component.exception.ExceptionView
import com.easyhz.noffice.core.design_system.component.loading.LoadingScreenProvider
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeScaffold
import com.easyhz.noffice.core.design_system.component.topBar.HomeTopBar
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Green500
import com.easyhz.noffice.core.design_system.theme.Grey50
import com.easyhz.noffice.core.design_system.util.exception.ExceptionType
import com.easyhz.noffice.core.model.organization.OrganizationSignUpInformation
import com.easyhz.noffice.feature.home.component.notice.NoticeView
import com.easyhz.noffice.feature.home.component.task.TaskView
import com.easyhz.noffice.feature.home.contract.home.HomeIntent
import com.easyhz.noffice.feature.home.contract.home.HomeSideEffect
import com.easyhz.noffice.feature.home.permission.checkNotificationPermission
import com.easyhz.noffice.feature.home.util.HomeTopBarMenu

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    snackBarHostState: SnackbarHostState,
    navigateToAnnouncementDetail: (Int, Int, String) -> Unit,
    navigateToOrganizationDetail: (Int, String) -> Unit,
    navigateToMyPage: () -> Unit,
    navigateToOrganizationJoin: (OrganizationSignUpInformation) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val organizationList = viewModel.organizationState.collectAsLazyPagingItems()
    val organizationIdToJoin = remember { DeepLinkManager.organizationIdToJoin }
    val context = LocalContext.current
    val isRefreshing = remember(organizationList.loadState.refresh) {
        organizationList.loadState.refresh == LoadState.Loading
    }

    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = { viewModel.postIntent(HomeIntent.Refresh) }
    )
    val requestPermissionLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                /* FIXME */
            } else {
                /* FIXME */
            }
        }

    LaunchedEffect(key1 = Unit) {
        checkNotificationPermission(
            context = context,
            launcher = requestPermissionLauncher
        ) { }
    }
    LaunchedEffect(key1 = organizationIdToJoin) {
        viewModel.postIntent(HomeIntent.JoinToOrganization(organizationIdToJoin))
    }
    LaunchedEffect(key1 = organizationList.loadState) {
        if (organizationList.loadState.prepend.endOfPaginationReached) {
            viewModel.postIntent(HomeIntent.SetInitLoading)
        }
    }
    LoadingScreenProvider(
        isLoading = uiState.isJoinLoading,
        navigationBarColor = Grey50
    ) {
        NofficeScaffold(
            modifier = modifier,
            topBar = {
                HomeTopBar(
                    tabs = enumValues<HomeTopBarMenu>(),
                    onClickIconMenu = {
                        viewModel.postIntent(HomeIntent.ClickTopBarIconMenu(it))
                    }
                ) {
                    viewModel.postIntent(HomeIntent.ChangeTopBarMenu(it))
                }
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .pullRefresh(pullRefreshState)
                    .padding(top = paddingValues.calculateTopPadding())
            ) {
                if (organizationList.itemCount == 0 && !isRefreshing) {
                    ExceptionView(
                        modifier = Modifier.fillMaxSize(),
                        type = ExceptionType.NO_ORGANIZATION
                    )
                }
                Crossfade(
                    targetState = uiState.topBarMenu,
                    animationSpec = tween(500),
                    label = "TopBarMenu"
                ) { screen ->
                    when (screen) {
                        HomeTopBarMenu.NOTICE -> {
                            NoticeView(
                                dayOfWeek = uiState.date,
                                name = uiState.name,
                                organizationList = organizationList,
                                isLoading = uiState.isInitLoading,
                                isRefreshing = isRefreshing,
                                onClickOrganizationHeader = {
                                    viewModel.postIntent(HomeIntent.ClickOrganizationHeader(it.id, it.name))
                                },
                                onClickAnnouncementCard = { organizationId, announcementId, announcementTitle ->
                                    viewModel.postIntent(
                                        HomeIntent.ClickAnnouncementCard(
                                            organizationId,
                                            announcementId,
                                            announcementTitle
                                        )
                                    )
                                }
                            )
                        }

                        HomeTopBarMenu.TASK -> {
                            TaskView(
                                modifier = Modifier
                                    .screenHorizonPadding()
                            ) { organizationId, organizationName ->
                                viewModel.postIntent(HomeIntent.ClickOrganizationHeader(organizationId, organizationName))
                            }
                        }
                    }
                }
                if (!uiState.isInitLoading) {
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
    }

    viewModel.sideEffect.collectInSideEffectWithLifecycle { sideEffect ->
        when (sideEffect) {
            is HomeSideEffect.NavigateToMyPage -> {
                navigateToMyPage()
            }

            is HomeSideEffect.NavigateToOrganizationDetail -> {
                navigateToOrganizationDetail(sideEffect.organizationId, sideEffect.organizationName)
            }
            is HomeSideEffect.NavigateToAnnouncementDetail -> {
                navigateToAnnouncementDetail(sideEffect.organizationId, sideEffect.announcementId, sideEffect.announcementTitle)
            }
            is HomeSideEffect.NavigateToOrganizationJoin -> {
                navigateToOrganizationJoin(sideEffect.organizationSignUpInformation)
            }

            is HomeSideEffect.ShowSnackBar -> {
                snackBarHostState.showSnackbar(
                    message = context.getString(sideEffect.stringId),
                    withDismissAction = true
                )
            }

            is HomeSideEffect.Refresh -> {
                organizationList.refresh()
            }
        }
    }
}