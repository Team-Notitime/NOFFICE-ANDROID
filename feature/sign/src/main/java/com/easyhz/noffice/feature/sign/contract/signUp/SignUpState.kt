package com.easyhz.noffice.feature.sign.contract.signUp

import com.easyhz.noffice.core.common.base.UiState
import com.easyhz.noffice.core.common.extension.toEnumMap
import com.easyhz.noffice.feature.sign.util.signUp.SignUpStep
import com.easyhz.noffice.feature.sign.util.signUp.Terms
import com.easyhz.noffice.feature.sign.util.signUp.toTermsMap
import java.util.EnumMap

data class SignUpState(
    val step: Step,
    val isEnabledButton: Boolean,
    val isCheckedAllTerms: Boolean,
    val termsStatusMap: EnumMap<Terms, Boolean>,
): UiState() {
    companion object {
        fun init() = SignUpState(
            step = Step(currentStep = SignUpStep.TERMS, previousStep = null),
            isEnabledButton = false,
            isCheckedAllTerms = false,
            termsStatusMap = Terms.entries.toTermsMap()
        )
    }

    fun SignUpState.updateTermsCheck(key: Terms): SignUpState {
        val newMap = termsStatusMap.toMutableMap().apply {
            this[key] = this[key]?.not() ?: false
        }.toEnumMap()
        val isCheckedAll = newMap.values.all { it }
        val isEnabledButton = Terms.entries.filter { it.isRequired }.all { newMap[it] == true }

        return copy(termsStatusMap = newMap, isCheckedAllTerms = isCheckedAll, isEnabledButton = isEnabledButton)
    }

    fun SignUpState.updateTermsAllCheck(): SignUpState {
        val newMap = termsStatusMap.mapValues { !isCheckedAllTerms }.toEnumMap()
        val isEnabledButton = Terms.entries.filter { it.isRequired }.all { newMap[it] == true }
        return copy(termsStatusMap = newMap, isCheckedAllTerms = !isCheckedAllTerms, isEnabledButton = isEnabledButton)
    }
}

data class Step(
    val currentStep: SignUpStep,
    val previousStep: SignUpStep?
)