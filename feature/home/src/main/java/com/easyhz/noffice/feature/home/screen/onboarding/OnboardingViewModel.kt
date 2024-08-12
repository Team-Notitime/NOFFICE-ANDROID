package com.easyhz.noffice.feature.home.screen.onboarding

import androidx.lifecycle.viewModelScope
import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.domain.home.usecase.onboarding.SetIsFirstRunUseCase
import com.easyhz.noffice.feature.home.contract.onboarding.OnboardingIntent
import com.easyhz.noffice.feature.home.contract.onboarding.OnboardingSideEffect
import com.easyhz.noffice.feature.home.contract.onboarding.OnboardingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val setIsFirstRunUseCase: SetIsFirstRunUseCase
): BaseViewModel<OnboardingState, OnboardingIntent, OnboardingSideEffect>(
    initialState = OnboardingState.init()
) {
    override fun handleIntent(intent: OnboardingIntent) {
        when(intent) {
            is OnboardingIntent.ClickButton -> { onClickButton(intent.index) }
        }
    }

    private fun onClickButton(index: Int) = viewModelScope.launch {
        if (index == currentState.steps.lastIndex) {
            setIsFirstRunUseCase.invoke(false).getOrNull()
            postSideEffect { OnboardingSideEffect.NavigateToLogin }
        } else {
            postSideEffect { OnboardingSideEffect.SlideNext(index + 1) }
        }
    }
}