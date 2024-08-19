package com.easyhz.noffice.feature.announcement.screen.creation.promotion

import com.easyhz.noffice.core.common.base.BaseViewModel
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
            is PromotionIntent.ClickBackButton -> { onClickBackButton() }
            is PromotionIntent.ClickSaveButton -> { saveButton() }
            is PromotionIntent.ClickPromotionCard -> { onClickPromotionCard(intent.cardImage) }
        }
    }

    private fun onClickBackButton() {
        postSideEffect { PromotionSideEffect.NavigateToUp }
    }

    private fun onClickPromotionCard(cardImage: CardImage) {
        if (currentState.selectCard == cardImage) return
        reduce { copy(selectCard = cardImage) }
    }

    private fun saveButton() {

    }
}