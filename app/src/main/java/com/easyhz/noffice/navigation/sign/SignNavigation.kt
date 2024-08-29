package com.easyhz.noffice.navigation.sign

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.easyhz.noffice.feature.sign.screen.login.LoginScreen
import com.easyhz.noffice.feature.sign.screen.signUp.SignUpScreen
import com.easyhz.noffice.navigation.home.navigateToHome
import com.easyhz.noffice.navigation.sign.screen.Login
import com.easyhz.noffice.navigation.sign.screen.SignUp

internal fun NavGraphBuilder.signGraph(
    navController: NavController,
    snackBarHostState: SnackbarHostState
) {
    composable<Login> {
        val navOptions = navOptions {
            popUpTo(navController.graph.startDestinationId) {
                inclusive = true
            }
            launchSingleTop = true
        }
        LoginScreen(
            snackBarHostState = snackBarHostState,
            navigateToHome = { navController.navigateToHome(navOptions) },
            navigateToSignUp = navController::navigateToSignUp
        )
    }
    composable<SignUp> {
        val navOptions = navOptions {
            popUpTo(navController.graph.startDestinationId) {
                inclusive = true
            }
            launchSingleTop = true
        }
        SignUpScreen(
            navigateToHome = { navController.navigateToHome(navOptions) }
        )
    }
}

internal fun NavController.navigateToLogin() {
    val navOptions = navOptions {
        popUpTo(this@navigateToLogin.graph.id) {
            inclusive = true
        }
        launchSingleTop = true
    }
    navigate(Login, navOptions)
}

internal fun NavController.navigateToSignUp() {
    val navOptions = navOptions {
        popUpTo(this@navigateToSignUp.graph.id) {
            inclusive = true
        }
        launchSingleTop = true
    }
    navigate(SignUp, navOptions)
}