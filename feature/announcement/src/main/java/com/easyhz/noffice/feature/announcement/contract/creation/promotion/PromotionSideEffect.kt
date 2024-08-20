package com.easyhz.noffice.feature.announcement.contract.creation.promotion

import com.easyhz.noffice.core.common.base.UiSideEffect

sealed class PromotionSideEffect: UiSideEffect() {
    data object NavigateToUp: PromotionSideEffect()
}