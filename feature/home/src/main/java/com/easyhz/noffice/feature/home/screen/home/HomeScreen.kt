package com.easyhz.noffice.feature.home.screen.home

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.easyhz.noffice.core.common.manager.DeepLinkManager
import com.easyhz.noffice.core.common.util.collectInSideEffectWithLifecycle
import com.easyhz.noffice.core.design_system.component.exception.ExceptionView
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeScaffold
import com.easyhz.noffice.core.design_system.component.topBar.HomeTopBar
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.util.exception.ExceptionType
import com.easyhz.noffice.feature.home.component.notice.NoticeView
import com.easyhz.noffice.feature.home.component.task.TaskView
import com.easyhz.noffice.feature.home.contract.home.HomeIntent
import com.easyhz.noffice.feature.home.contract.home.HomeSideEffect
import com.easyhz.noffice.feature.home.permission.checkNotificationPermission
import com.easyhz.noffice.feature.home.util.HomeTopBarMenu

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToAnnouncementDetail: (Int, Int, String) -> Unit,
    navigateToMyPage: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val organizationList = viewModel.organizationState.collectAsLazyPagingItems()
    val organizationIdToJoin = remember { DeepLinkManager.organizationIdToJoin }
    val context = LocalContext.current
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
        println("id >>>>>> $organizationIdToJoin")
    }
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
        if(organizationList.itemCount == 0 && organizationList.loadState.refresh != LoadState.Loading) {
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
                        modifier = Modifier.padding(top = paddingValues.calculateTopPadding()),
                        dayOfWeek = uiState.dayOfWeek,
                        name = uiState.name,
                        organizationList = organizationList,
                        navigateToAnnouncementDetail = navigateToAnnouncementDetail
                    )
                }

                HomeTopBarMenu.TASK -> {
                    TaskView(modifier = Modifier
                        .padding(top = paddingValues.calculateTopPadding())
                        .screenHorizonPadding())
                }
            }
        }
    }

    viewModel.sideEffect.collectInSideEffectWithLifecycle { sideEffect ->
        when(sideEffect) {
            HomeSideEffect.NavigateToMyPage -> { navigateToMyPage() }
        }
    }
}