package com.easyhz.noffice.feature.home.contract.onboarding

import com.easyhz.noffice.core.common.base.UiIntent

sealed class OnboardingIntent: UiIntent() {
    data class ClickButton(val index: Int): OnboardingIntent()
}