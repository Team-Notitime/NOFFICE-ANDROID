package com.easyhz.noffice.feature.home.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.easyhz.noffice.core.common.util.collectInSideEffectWithLifecycle
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeBasicScaffold
import com.easyhz.noffice.core.design_system.theme.Grey900
import com.easyhz.noffice.feature.home.contract.splash.SplashSideEffect

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel = hiltViewModel()
) {
    NofficeBasicScaffold(
        containerColor = Grey900,
        statusBarColor = Grey900,
        navigationBarColor = Grey900,
    ) { _ ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Grey900),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_splash_logo),
                contentDescription = "splash"
            )
        }
    }

    viewModel.sideEffect.collectInSideEffectWithLifecycle { sideEffect ->
        when(sideEffect) {
            is SplashSideEffect.NavigateToOnboarding -> { }
            is SplashSideEffect.NavigateToLogin -> { }
            is SplashSideEffect.NavigateToHome -> { }
            is SplashSideEffect.NavigateToDeepLink -> { }
        }
    }
}