package com.easyhz.noffice.feature.announcement.contract.creation.promotion

import androidx.annotation.StringRes
import com.easyhz.noffice.core.common.base.UiSideEffect

sealed class PromotionSideEffect: UiSideEffect() {
    data object NavigateToUp: PromotionSideEffect()
    data object HidePromotionBottomSheet: PromotionSideEffect()
    data class ScrollToItem(val index: Int): PromotionSideEffect()
    data class ShowSnackBar(@StringRes val stringId: Int): PromotionSideEffect()
    data class NavigateToSuccess(val id: Int, val title: String): PromotionSideEffect()
}