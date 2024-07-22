package com.easyhz.noffice.navigation.home

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.easyhz.noffice.feature.home.screen.home.HomeScreen
import com.easyhz.noffice.navigation.home.screen.Home

internal fun NavGraphBuilder.homeScreen(
    modifier: Modifier
) {
    composable<Home> {
        HomeScreen(modifier = modifier)
    }
}

internal fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    val mNavOptions = navOptions
        ?: navOptions {
            popUpTo(this@navigateToHome.graph.id) {
                inclusive = true
            }
        }
    navigate(
        route = Home,
        navOptions = mNavOptions
    )
}