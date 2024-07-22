package com.easyhz.noffice.feature.announcement.screen.creation

import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.feature.announcement.contract.creation.CreationIntent
import com.easyhz.noffice.feature.announcement.contract.creation.CreationSideEffect
import com.easyhz.noffice.feature.announcement.contract.creation.CreationState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreationViewModel @Inject constructor(

): BaseViewModel<CreationState, CreationIntent, CreationSideEffect>(
    initialState = CreationState.init()
) {
    override fun handleIntent(intent: CreationIntent) {
        when(intent) {
            is CreationIntent.ClickBackButton -> { onClickBackButton() }
            is CreationIntent.ClickNextButton -> { onClickNextButton() }
        }
    }

    private fun onClickBackButton() {
        postSideEffect { CreationSideEffect.NavigateToUp }
    }

    private fun onClickNextButton() {
        postSideEffect { CreationSideEffect.NavigateToNext }
    }
}