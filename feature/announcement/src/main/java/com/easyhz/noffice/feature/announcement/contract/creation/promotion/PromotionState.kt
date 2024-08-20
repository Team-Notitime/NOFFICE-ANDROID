package com.easyhz.noffice.feature.announcement.contract.creation.promotion

import androidx.annotation.DrawableRes
import com.easyhz.noffice.core.common.base.UiState
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.model.announcement.param.AnnouncementParam

data class PromotionState(
    val announcementParam: AnnouncementParam?,
    val selectCard: CardImage,
    val bottomSheetSelectCard: CardImage,
    val isShowPromotionBottomSheet: Boolean,
    val hasPromotion: Boolean,
    val isLoading: Boolean
): UiState() {
    companion object {
        fun init() = PromotionState(
            announcementParam = null,
            selectCard = CardImage.CARD1,
            bottomSheetSelectCard = CardImage.CARD1,
            isShowPromotionBottomSheet = false,
            hasPromotion = false,
            isLoading = false
        )
    }
}

enum class CardImage(
    @DrawableRes val imageId: Int,
    val isPromotion: Boolean
) {
    CARD1(
        imageId = R.drawable.illust1,
        isPromotion = false
    ), CARD2(
        imageId = R.drawable.illust2,
        isPromotion = false
    ), CARD3(
        imageId = R.drawable.illust3,
        isPromotion = true
    ), CARD4(
        imageId = R.drawable.illust4,
        isPromotion = true
    ),
}