package com.easyhz.noffice.feature.home.util

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.easyhz.noffice.core.design_system.R

enum class OnboardingStep(
    @StringRes val titleId: Int,
    @StringRes val contentId: Int,
    @DrawableRes val imageId: Int
) {
    STEP1(
        titleId = R.string.onboarding_step_1_title,
        contentId = R.string.onboarding_step_1_content,
        imageId = R.drawable.onboarding_step1
    ), STEP2(
        titleId = R.string.onboarding_step_2_title,
        contentId = R.string.onboarding_step_2_content,
        imageId = R.drawable.onboarding_step2
    ), STEP3(
        titleId = R.string.onboarding_step_3_title,
        contentId = R.string.onboarding_step_3_content,
        imageId = R.drawable.onboarding_step3
    )
}