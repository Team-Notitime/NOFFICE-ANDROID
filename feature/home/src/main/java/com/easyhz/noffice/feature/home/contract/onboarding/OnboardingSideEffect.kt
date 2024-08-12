package com.easyhz.noffice.feature.home.contract.onboarding

import com.easyhz.noffice.core.common.base.UiSideEffect

sealed class OnboardingSideEffect: UiSideEffect() {
    data class SlideNext(val page: Int): OnboardingSideEffect()
    data object NavigateToLogin: OnboardingSideEffect()
}