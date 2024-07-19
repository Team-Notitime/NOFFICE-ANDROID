package com.easyhz.noffice.feature.organization.contract.creation

import androidx.compose.ui.text.input.TextFieldValue
import com.easyhz.noffice.core.common.base.UiState
import com.easyhz.noffice.core.common.util.Step
import com.easyhz.noffice.core.common.util.toEnabledStepButton
import com.easyhz.noffice.feature.organization.util.creation.CreationStep
import java.util.EnumMap

data class CreationState(
    val step: Step<CreationStep>,
    val enabledStepButton: EnumMap<CreationStep, Boolean>,
    val groupName: TextFieldValue
): UiState() {
    companion object {
        const val GROUP_NAME_MAX = 10
        fun init() = CreationState(
            step = Step(currentStep = CreationStep.GROUP_NAME, previousStep = null),
            enabledStepButton = CreationStep.entries.toEnabledStepButton(),
            groupName = TextFieldValue("")
        )
    }

    fun CreationState.updateStep(currentStep: CreationStep): CreationState = this.copy(
        step = step.copy(
            previousStep = step.currentStep,
            currentStep = currentStep,
        ),
    )

}