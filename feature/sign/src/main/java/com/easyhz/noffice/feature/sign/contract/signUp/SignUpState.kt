package com.easyhz.noffice.feature.sign.contract.signUp

import com.easyhz.noffice.core.common.base.UiState
import com.easyhz.noffice.core.common.extension.toEnumMap
import com.easyhz.noffice.core.common.util.Step
import com.easyhz.noffice.core.common.util.toEnabledStepButton
import com.easyhz.noffice.core.common.util.updateStepButton
import com.easyhz.noffice.core.design_system.util.terms.TermsType
import com.easyhz.noffice.feature.sign.util.signUp.SignUpStep
import com.easyhz.noffice.feature.sign.util.signUp.Terms
import com.easyhz.noffice.feature.sign.util.signUp.toTermsMap
import java.util.EnumMap

data class SignUpState(
    val step: Step<SignUpStep>,
    val enabledStepButton: EnumMap<SignUpStep, Boolean>,
    val isCheckedAllTerms: Boolean,
    val termsStatusMap: EnumMap<Terms, Boolean>,
    val name: String,
    val isShowTermsBottomSheet: Boolean,
    val selectedTerms: TermsType,
    val isLoading: Boolean
) : UiState() {
    companion object {
        fun init() = SignUpState(
            step = Step(currentStep = SignUpStep.TERMS, previousStep = null),
            enabledStepButton = SignUpStep.entries.toEnabledStepButton(),
            isCheckedAllTerms = false,
            termsStatusMap = Terms.entries.toTermsMap(),
            name = "",
            isShowTermsBottomSheet = false,
            selectedTerms = TermsType.SERVICE_OF_TERMS,
            isLoading = false
        )
    }

    fun SignUpState.updateStep(currentStep: SignUpStep): SignUpState = this.copy(
        step = step.copy(
            previousStep = step.currentStep,
            currentStep = currentStep,
        ),
    )

    fun SignUpState.updateTermsCheck(key: Terms): SignUpState {
        val newMap = termsStatusMap.toMutableMap().apply {
            this[key] = this[key]?.not() ?: false
        }.toEnumMap()
        val isCheckedAll = newMap.values.all { it }
        val isEnabledButton = Terms.entries.filter { it.isRequired }.all { newMap[it] == true }

        return copy(
            termsStatusMap = newMap,
            isCheckedAllTerms = isCheckedAll,
            enabledStepButton = enabledStepButton.updateStepButton(step.currentStep, isEnabledButton)
        )
    }

    fun SignUpState.updateTermsAllCheck(): SignUpState {
        val newMap = termsStatusMap.mapValues { !isCheckedAllTerms }.toEnumMap()
        val isEnabledButton = Terms.entries.filter { it.isRequired }.all { newMap[it] == true }
        return copy(
            termsStatusMap = newMap,
            isCheckedAllTerms = !isCheckedAllTerms,
            enabledStepButton = enabledStepButton.updateStepButton(step.currentStep, isEnabledButton)
        )
    }
}