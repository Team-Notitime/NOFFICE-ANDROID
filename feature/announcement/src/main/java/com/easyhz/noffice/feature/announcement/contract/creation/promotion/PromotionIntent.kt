package com.easyhz.noffice.feature.announcement.contract.creation.promotion

import com.easyhz.noffice.core.common.base.UiIntent

sealed class PromotionIntent: UiIntent() {
    data object ClickBackButton: PromotionIntent()
    data object ClickSaveButton: PromotionIntent()
    data class ClickPromotionCard(val cardImage: CardImage): PromotionIntent()
}