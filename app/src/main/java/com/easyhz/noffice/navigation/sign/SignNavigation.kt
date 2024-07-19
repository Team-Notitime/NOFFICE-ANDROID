package com.easyhz.noffice.navigation.sign

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.easyhz.noffice.feature.sign.screen.signUp.SignUpScreen
import com.easyhz.noffice.navigation.sign.screen.SignUp

internal fun NavGraphBuilder.signScreen() {
    composable<SignUp> {
        SignUpScreen()
    }
}