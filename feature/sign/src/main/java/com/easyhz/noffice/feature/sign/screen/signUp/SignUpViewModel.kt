package com.easyhz.noffice.feature.sign.screen.signUp

import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.core.common.util.updateStepButton
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
            is SignUpIntent.ClickBackButton -> { onClickBackButton() }
            is SignUpIntent.ClickNextButton -> { onClickNextButton() }
            is SignUpIntent.ClickTermsAllCheck -> { onClickTermsAllCheck() }
            is SignUpIntent.ClickTermsCheck -> { onClickTermsCheck(intent.terms) }
            is SignUpIntent.ClickTermsDetail -> { /* TODO */ }
            is SignUpIntent.ChangeNameTextValue -> { onChangeNameTextValue(intent.text) }
            is SignUpIntent.ClearFocus -> { onClearFocus() }
        }
    }

    private fun onClickBackButton() {
        currentState.step.currentStep.beforeStep()?.let { beforeStep ->
            reduce { updateStep(currentStep = beforeStep) }
        }
    }

    private fun onClickNextButton() {
        currentState.step.currentStep.nextStep()?.let { nextStep ->
            reduce { updateStep(currentStep = nextStep) }
        } ?: run {
           postSideEffect { SignUpSideEffect.NavigateToHome }
        }
    }


    private fun onClickTermsAllCheck() {
        reduce { updateTermsAllCheck() }
    }

    private fun onClickTermsCheck(terms: Terms) {
        reduce { updateTermsCheck(terms) }
    }

    private fun onChangeNameTextValue(newText: String) {
        val isEnabledButton = newText.isNotBlank()
        reduce { copy(name = newText, enabledStepButton = enabledStepButton.updateStepButton(step.currentStep, isEnabledButton)) }
    }

    private fun onClearFocus() {
        postSideEffect { SignUpSideEffect.ClearFocus }
    }
}