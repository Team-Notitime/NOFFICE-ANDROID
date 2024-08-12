package com.easyhz.noffice.navigation.sign

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.easyhz.noffice.feature.sign.screen.login.LoginScreen
import com.easyhz.noffice.feature.sign.screen.signUp.SignUpScreen
import com.easyhz.noffice.navigation.home.navigateToHome
import com.easyhz.noffice.navigation.sign.screen.LogIn
import com.easyhz.noffice.navigation.sign.screen.SignUp

internal fun NavGraphBuilder.signGraph(
    navController: NavController
) {
    composable<LogIn> {
        val navOptions = navOptions {
            popUpTo(navController.graph.startDestinationId) {
                inclusive = true
            }
            launchSingleTop = true
        }
        LoginScreen(
            navigateToHome = { navController.navigateToHome(navOptions) }
        )
    }
    composable<SignUp> {
        SignUpScreen()
    }
}