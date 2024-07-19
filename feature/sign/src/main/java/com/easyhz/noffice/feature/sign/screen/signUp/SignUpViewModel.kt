package com.easyhz.noffice.feature.sign.screen.signUp

import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.feature.sign.contract.signUp.SignUpIntent
import com.easyhz.noffice.feature.sign.contract.signUp.SignUpSideEffect
import com.easyhz.noffice.feature.sign.contract.signUp.SignUpState
import com.easyhz.noffice.feature.sign.util.signUp.Terms
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(

): BaseViewModel<SignUpState, SignUpIntent, SignUpSideEffect>(
    initialState = SignUpState.init()
) {
    override fun handleIntent(intent: SignUpIntent) {
        when(intent) {
            is SignUpIntent.ClickTermsAllCheck -> { onClickTermsAllCheck() }
            is SignUpIntent.ClickTermsCheck -> { onClickTermsCheck(intent.terms) }
            is SignUpIntent.ClickTermsDetail -> { /* TODO */ }
        }
    }

    private fun onClickTermsAllCheck() {
        reduce { updateTermsAllCheck() }
    }

    private fun onClickTermsCheck(terms: Terms) {
        reduce { updateTermsCheck(terms) }
    }
}