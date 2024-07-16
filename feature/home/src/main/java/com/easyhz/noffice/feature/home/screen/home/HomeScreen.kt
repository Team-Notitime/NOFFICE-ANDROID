package com.easyhz.noffice.feature.home.screen.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeScaffold
import com.easyhz.noffice.core.design_system.component.topBar.HomeTopBar
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
    ) {
        AnimatedContent(
            targetState = uiState.topBarMenu,
            transitionSpec = {
               fadeIn() togetherWith
                fadeOut()
            }, label = "TopBarMenu"
        ) { targetMenu ->
            when (targetMenu) {
                HomeTopBarMenu.NOTICE -> {
                    // TODO : NOTICE
                }

                HomeTopBarMenu.TASK -> {
                    // TODO : TASK
                }
            }
        }
    }
}