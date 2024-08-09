package com.easyhz.noffice.navigation.home

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.easyhz.noffice.feature.home.screen.home.HomeScreen
import com.easyhz.noffice.navigation.home.screen.Home

internal fun NavGraphBuilder.homeGraph(
    modifier: Modifier,
    navigateToAnnouncementDetail: (Int, String) -> Unit,
    navigateToMyPage: () -> Unit
) {
    composable<Home>(
        enterTransition = { fadeIn(animationSpec = tween(700)) },
        exitTransition = { fadeOut(animationSpec = tween(700)) },
        popEnterTransition = { fadeIn(animationSpec = tween(700)) },
        popExitTransition = { fadeOut(animationSpec = tween(700)) }
    ) {
        HomeScreen(
            modifier = modifier,
            navigateToAnnouncementDetail = navigateToAnnouncementDetail,
            navigateToMyPage = navigateToMyPage
        )
    }
}

internal fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    navigate(
        route = Home,
        navOptions = navOptions
    )
}