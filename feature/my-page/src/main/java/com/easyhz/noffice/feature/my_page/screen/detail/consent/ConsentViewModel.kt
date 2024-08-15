package com.easyhz.noffice.feature.my_page.screen.detail.consent

import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.feature.my_page.contract.detail.consent.ConsentIntent
import com.easyhz.noffice.feature.my_page.contract.detail.consent.ConsentSideEffect
import com.easyhz.noffice.feature.my_page.contract.detail.consent.ConsentState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConsentViewModel @Inject constructor(

): BaseViewModel<ConsentState, ConsentIntent, ConsentSideEffect>(
    initialState = ConsentState.init()
) {
    override fun handleIntent(intent: ConsentIntent) {
        when(intent) {
            is ConsentIntent.ClickBackButton -> { onClickBackButton() }
            is ConsentIntent.ClickToggleButton -> { onClickToggleButton() }
        }
    }

    private fun onClickBackButton() {
        postSideEffect { ConsentSideEffect.NavigateToUp }
    }

    private fun onClickToggleButton() {
        reduce { copy(isChecked = !isChecked) }
    }
}
