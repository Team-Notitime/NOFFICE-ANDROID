package com.easyhz.noffice.feature.home.screen.onboarding

import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.feature.home.contract.onboarding.OnboardingIntent
import com.easyhz.noffice.feature.home.contract.onboarding.OnboardingSideEffect
import com.easyhz.noffice.feature.home.contract.onboarding.OnboardingState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(

): BaseViewModel<OnboardingState, OnboardingIntent, OnboardingSideEffect>(
    initialState = OnboardingState.init()
) {
    override fun handleIntent(intent: OnboardingIntent) {
        when(intent) {
            is OnboardingIntent.ClickButton -> { onClickButton(intent.index) }
        }
    }

    private fun onClickButton(index: Int) {
        if (index == currentState.steps.lastIndex) {
            postSideEffect { OnboardingSideEffect.NavigateToLogin }
        } else {
            postSideEffect { OnboardingSideEffect.SlideNext(index + 1) }
        }
    }
}