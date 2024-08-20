package com.easyhz.noffice.feature.announcement.screen.creation.promotion

import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.core.model.announcement.param.AnnouncementParam
import com.easyhz.noffice.feature.announcement.contract.creation.promotion.CardImage
import com.easyhz.noffice.feature.announcement.contract.creation.promotion.PromotionIntent
import com.easyhz.noffice.feature.announcement.contract.creation.promotion.PromotionSideEffect
import com.easyhz.noffice.feature.announcement.contract.creation.promotion.PromotionState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PromotionViewModel @Inject constructor(

): BaseViewModel<PromotionState, PromotionIntent, PromotionSideEffect>(
    initialState = PromotionState.init()
) {
    override fun handleIntent(intent: PromotionIntent) {
        when(intent) {
            is PromotionIntent.InitScreen -> { initScreen(intent.param) }
            is PromotionIntent.ClickBackButton -> { onClickBackButton() }
            is PromotionIntent.ClickSaveButton -> { saveButton() }
            is PromotionIntent.ClickPromotionCard -> { onClickPromotionCard(intent.cardImage) }
            is PromotionIntent.HideUserNameBottomSheet -> { hideBottomSheet() }
            is PromotionIntent.SetPromotionBottomSheet -> { setBottomSheet(intent.isShow) }
            is PromotionIntent.ClickBottomSheetCard -> { onClickBottomSheetCard(intent.cardImage) }
            is PromotionIntent.ClickBottomSheetSelectButton -> { onClickBottomSheetSelectButton() }
        }
    }

    private fun initScreen(param: AnnouncementParam) {
        reduce { copy(announcementParam = param) }
    }

    private fun onClickBackButton() {
        postSideEffect { PromotionSideEffect.NavigateToUp }
    }

    private fun onClickPromotionCard(cardImage: CardImage) {
        if (currentState.selectCard == cardImage) return
        if (!currentState.hasPromotion && cardImage.isPromotion) {
            setBottomSheet(true)
        } else {
            reduce { copy(selectCard = cardImage, bottomSheetSelectCard = cardImage) }
            scrollToItem()
        }
    }

    private fun onClickBottomSheetCard(cardImage: CardImage) {
        if (currentState.bottomSheetSelectCard == cardImage) return
        reduce { copy(bottomSheetSelectCard = cardImage) }
    }

    private fun onClickBottomSheetSelectButton() {
        hideBottomSheet()
        reduce { copy(selectCard = bottomSheetSelectCard) }
        scrollToItem()
    }

    private fun hideBottomSheet() {
        postSideEffect { PromotionSideEffect.HidePromotionBottomSheet }
    }

    private fun setBottomSheet(isShow: Boolean) {
        reduce { copy(isShowPromotionBottomSheet = isShow) }
    }

    private fun scrollToItem() {
        val index = CardImage.entries.indexOf(currentState.selectCard)
        postSideEffect { PromotionSideEffect.ScrollToItem(index) }
    }

    private fun saveButton() {

    }
}