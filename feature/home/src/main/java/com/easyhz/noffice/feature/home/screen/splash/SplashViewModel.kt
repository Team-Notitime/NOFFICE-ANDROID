package com.easyhz.noffice.feature.home.screen.splash

import androidx.lifecycle.viewModelScope
import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.core.model.splash.EnterScreenType
import com.easyhz.noffice.domain.home.usecase.splash.GetSplashInfoUseCase
import com.easyhz.noffice.feature.home.contract.splash.SplashIntent
import com.easyhz.noffice.feature.home.contract.splash.SplashSideEffect
import com.easyhz.noffice.feature.home.contract.splash.SplashState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getSplashInfoUseCase: GetSplashInfoUseCase
):BaseViewModel<SplashState, SplashIntent, SplashSideEffect>(
    initialState = SplashState
) {
    override fun handleIntent(intent: SplashIntent) { }

    init {
        getSplashInfo()
    }

    private fun getSplashInfo() = viewModelScope.launch {
        getSplashInfoUseCase.invoke(Unit).onSuccess {
            delay(300)
            when(it) {
                EnterScreenType.ONBOARDING -> { navigateToOnboarding() }
                EnterScreenType.LOGIN -> { navigateToLogin() }
                EnterScreenType.HOME -> { navigateToHome() }
            }
        }
    }

    private fun navigateToOnboarding() {
        postSideEffect { SplashSideEffect.NavigateToOnboarding }
    }

    private fun navigateToLogin() {
        postSideEffect { SplashSideEffect.NavigateToLogin }
    }

    private fun navigateToHome() {
        postSideEffect { SplashSideEffect.NavigateToHome }
    }

    private fun navigateToDeepLink() {
        postSideEffect { SplashSideEffect.NavigateToDeepLink }
    }
}