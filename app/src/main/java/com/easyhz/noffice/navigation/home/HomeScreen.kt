package com.easyhz.noffice.navigation.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.easyhz.noffice.feature.home.HomeScreen
import com.easyhz.noffice.navigation.home.screen.Home

internal fun NavGraphBuilder.homeScreen() {
    composable<Home> {
        HomeScreen()
    }
}

internal fun NavController.navigateToHome(navOptions: NavOptions) {
    navigate(
        route = Home,
        navOptions = navOptions
    )
}