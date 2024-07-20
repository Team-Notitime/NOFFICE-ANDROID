package com.easyhz.noffice.feature.home.screen.home

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeScaffold
import com.easyhz.noffice.core.design_system.component.topBar.HomeTopBar
import com.easyhz.noffice.feature.home.component.notice.NoticeView
import com.easyhz.noffice.feature.home.component.task.TaskView
import com.easyhz.noffice.feature.home.contract.home.HomeIntent
import com.easyhz.noffice.feature.home.util.HomeTopBarMenu

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    NofficeScaffold(
        modifier = modifier,
        topBar = {
            HomeTopBar(
                tabs = enumValues<HomeTopBarMenu>(),
                onClickIconMenu = { }
            ) {
                viewModel.postIntent(HomeIntent.ChangeTopBarMenu(it))
            }
        }
    ) { paddingValues ->
        Crossfade(
            targetState = uiState.topBarMenu,
            animationSpec = tween(500),
            label = "TopBarMenu"
        ) { screen ->
            when (screen) {
                HomeTopBarMenu.NOTICE -> {
                    NoticeView(modifier = Modifier.padding(top = paddingValues.calculateTopPadding()))
                }

                HomeTopBarMenu.TASK -> {
                    TaskView(modifier = Modifier.padding(top = paddingValues.calculateTopPadding()))
                }
            }
        }
    }
}