package com.easyhz.noffice.navigation.home

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.easyhz.noffice.feature.home.screen.home.HomeScreen
import com.easyhz.noffice.feature.home.screen.onboarding.OnboardingScreen
import com.easyhz.noffice.feature.home.screen.splash.SplashScreen
import com.easyhz.noffice.navigation.announcement.navigateToAnnouncementDetail
import com.easyhz.noffice.navigation.home.screen.Home
import com.easyhz.noffice.navigation.home.screen.Onboarding
import com.easyhz.noffice.navigation.home.screen.Splash
import com.easyhz.noffice.navigation.my_page.navigateToMyPage
import com.easyhz.noffice.navigation.sign.navigateToLogIn
import com.easyhz.noffice.navigation.sign.screen.LogIn

internal fun NavGraphBuilder.homeGraph(
    modifier: Modifier,
    navController: NavController,
) {
    composable<Splash> {
        val navOptions = navOptions {
            popUpTo(navController.graph.id) { inclusive = true }
        }
        SplashScreen(
            navigateToOnboarding = { navController.navigateToOnboarding(navOptions) },
            navigateToLogin = navController::navigateToLogIn,
            navigateToHome = { navController.navigateToHome(navOptions) },
        )
    }
    composable<Onboarding> {
        OnboardingScreen(
            navigateToLogin = navController::navigateToLogIn
        )
    }
    composable<Home>(
        enterTransition = {
            if (this.enabledSlide()) null
            else fadeIn(animationSpec = tween(700))
        },
        exitTransition = { fadeOut(animationSpec = tween(700)) },
        popEnterTransition = { fadeIn(animationSpec = tween(700)) },
        popExitTransition = { fadeOut(animationSpec = tween(700)) }
    ) {
        HomeScreen(
            modifier = modifier,
            navigateToAnnouncementDetail = navController::navigateToAnnouncementDetail,
            navigateToMyPage = navController::navigateToMyPage
        )
    }
}

internal fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    navigate(
        route = Home,
        navOptions = navOptions
    )
}

internal fun NavController.navigateToOnboarding(navOptions: NavOptions?) {
    navigate(route = Onboarding, navOptions)
}

private fun AnimatedContentTransitionScope<NavBackStackEntry>.enabledSlide() =
    this.initialState.destination.route == LogIn::class.java.name
