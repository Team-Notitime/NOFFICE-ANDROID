package com.easyhz.noffice.feature.announcement.contract.creation.promotion

import com.easyhz.noffice.core.common.base.UiIntent
import com.easyhz.noffice.core.model.announcement.param.AnnouncementParam

sealed class PromotionIntent: UiIntent() {
    data class InitScreen(val param: AnnouncementParam): PromotionIntent()
    data object ClickBackButton: PromotionIntent()
    data object ClickSaveButton: PromotionIntent()
    data class ClickPromotionCard(val cardImage: CardImage): PromotionIntent()
}