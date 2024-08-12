package com.easyhz.noffice.feature.home.contract.onboarding

import com.easyhz.noffice.core.common.base.UiState
import com.easyhz.noffice.feature.home.util.OnboardingStep

data class OnboardingState(
    val steps: List<OnboardingStep>
): UiState() {
    companion object {
        fun init() = OnboardingState(
            steps = enumValues<OnboardingStep>().toList()
        )
    }
}
