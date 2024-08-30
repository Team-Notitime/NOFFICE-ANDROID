package com.easyhz.noffice.feature.sign.screen.signUp

import androidx.lifecycle.viewModelScope
import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.core.common.error.NofficeError
import com.easyhz.noffice.core.common.error.handleError
import com.easyhz.noffice.core.common.util.errorLogging
import com.easyhz.noffice.core.common.util.updateStepButton
import com.easyhz.noffice.core.design_system.util.terms.TermsType
import com.easyhz.noffice.domain.my_page.usecase.UpdateUserAliasUseCase
import com.easyhz.noffice.domain.sign.usecase.SetMemberNameUseCase
import com.easyhz.noffice.feature.sign.contract.signUp.SignUpIntent
import com.easyhz.noffice.feature.sign.contract.signUp.SignUpSideEffect
import com.easyhz.noffice.feature.sign.contract.signUp.SignUpState
import com.easyhz.noffice.feature.sign.util.signUp.Terms
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val updateUserAliasUseCase: UpdateUserAliasUseCase,
    private val setMemberNameUseCase: SetMemberNameUseCase
): BaseViewModel<SignUpState, SignUpIntent, SignUpSideEffect>(
    initialState = SignUpState.init()
) {
    override fun handleIntent(intent: SignUpIntent) {
        when(intent) {
            is SignUpIntent.ClickBackButton -> { onClickBackButton() }
            is SignUpIntent.ClickNextButton -> { onClickNextButton() }
            is SignUpIntent.ClickTermsAllCheck -> { onClickTermsAllCheck() }
            is SignUpIntent.ClickTermsCheck -> { onClickTermsCheck(intent.terms) }
            is SignUpIntent.ClickTermsDetail -> { onClickTermsDetail(intent.terms) }
            is SignUpIntent.ChangeNameTextValue -> { onChangeNameTextValue(intent.text) }
            is SignUpIntent.ClearFocus -> { onClearFocus() }
            is SignUpIntent.HideTermsBottomSheet -> { hideBottomSheet() }
            is SignUpIntent.SetTermsBottomSheet -> { setBottomSheet(intent.isShow) }
        }
    }

    private fun onClickBackButton() {
        currentState.step.currentStep.beforeStep()?.let { beforeStep ->
            reduce { updateStep(currentStep = beforeStep) }
        } ?: run {
            navigateToUp()
        }
    }

    private fun onClickNextButton() {
        currentState.step.currentStep.nextStep()?.let { nextStep ->
            reduce { updateStep(currentStep = nextStep) }
        } ?: run {
            handleSubmit()
        }
    }

    private fun handleSubmit() = viewModelScope.launch {
        setLoading(true)
        try {
            updateUserAliasUseCase.invoke(currentState.name).getOrElse {
                if (it is NofficeError.NoContent) return@getOrElse
                throw it
            }
            setMemberNameUseCase(currentState.name).getOrThrow()
            postSideEffect { SignUpSideEffect.NavigateToHome }
        } catch (exception: Throwable) {
            handleFailure(exception)
        } finally {
            setLoading(false)
        }
    }

    private fun setLoading(isLoading: Boolean) {
        reduce { copy(isLoading = isLoading) }
    }

    private fun handleFailure(exception: Throwable) {
        errorLogging(this.javaClass.name, "handleSubmit", exception)
        postSideEffect { SignUpSideEffect.ShowSnackBar(exception.handleError()) }
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

    private fun onClickTermsDetail(terms: Terms) {
        val termsType = TermsType.valueOf(terms.name)
        reduce { copy(selectedTerms = termsType) }
        setBottomSheet(true)
    }

    private fun setBottomSheet(isShow: Boolean) {
        reduce { copy(isShowTermsBottomSheet = isShow) }
    }

    private fun hideBottomSheet() {
        postSideEffect { SignUpSideEffect.HideTermsBottomSheet }
    }

    private fun navigateToUp() {
        postSideEffect { SignUpSideEffect.NavigateToUp }
    }
}