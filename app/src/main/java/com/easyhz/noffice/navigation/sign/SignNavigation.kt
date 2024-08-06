package com.easyhz.noffice.navigation.sign

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.easyhz.noffice.feature.sign.screen.login.LoginScreen
import com.easyhz.noffice.feature.sign.screen.signUp.SignUpScreen
import com.easyhz.noffice.navigation.sign.screen.LogIn
import com.easyhz.noffice.navigation.sign.screen.SignUp

internal fun NavGraphBuilder.signGraph(
    navigateToHome: () -> Unit
) {
    composable<LogIn> {
        LoginScreen(
            navigateToHome = navigateToHome
        )
    }
    composable<SignUp> {
        SignUpScreen()
    }
}