package com.easyhz.noffice.feature.home.contract.splash

import com.easyhz.noffice.core.common.base.UiSideEffect

sealed class SplashSideEffect: UiSideEffect() {
    data object NavigateToHome: SplashSideEffect()
    data object NavigateToOnboarding: SplashSideEffect()
    data object NavigateToLogin: SplashSideEffect()
    data object NavigateToDeepLink: SplashSideEffect()

    // FIXME 약관 창 가야 하는지 질문
}