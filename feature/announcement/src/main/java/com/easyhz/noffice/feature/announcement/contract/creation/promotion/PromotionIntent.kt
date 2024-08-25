package com.easyhz.noffice.feature.announcement.contract.creation.promotion

import com.easyhz.noffice.core.common.base.UiIntent
import com.easyhz.noffice.core.model.announcement.param.AnnouncementParam

sealed class PromotionIntent: UiIntent() {
    data class InitScreen(val param: AnnouncementParam): PromotionIntent()
    data object ClickBackButton: PromotionIntent()
    data object ClickSaveButton: PromotionIntent()
    data class ClickPromotionCard(val index: Int): PromotionIntent()
    data object HideUserNameBottomSheet: PromotionIntent()
    data class SetPromotionBottomSheet(val isShow: Boolean): PromotionIntent()
    data class ClickBottomSheetCard(val index: Int): PromotionIntent()
    data object ClickBottomSheetSelectButton: PromotionIntent()
}