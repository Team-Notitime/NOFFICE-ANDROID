package com.easyhz.noffice.feature.organization.screen.creation

import androidx.compose.ui.text.input.TextFieldValue
import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.core.common.util.updateStepButton
import com.easyhz.noffice.feature.organization.contract.creation.CreationIntent
import com.easyhz.noffice.feature.organization.contract.creation.CreationSideEffect
import com.easyhz.noffice.feature.organization.contract.creation.CreationState
import com.easyhz.noffice.feature.organization.contract.creation.CreationState.Companion.GROUP_NAME_MAX
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrganizationCreationViewModel @Inject constructor(

) : BaseViewModel<CreationState, CreationIntent, CreationSideEffect>(
    initialState = CreationState.init()
) {
    override fun handleIntent(intent: CreationIntent) {
        when (intent) {
            is CreationIntent.ClickBackButton -> { onClickBackButton() }
            is CreationIntent.ClickNextButton -> { onClickNextButton() }
            is CreationIntent.ChangeGroupNameTextValue -> { onChangeGroupNameTextValue(intent.text) }
            is CreationIntent.ClearGroupName -> { onClearGroupName() }
            is CreationIntent.ClearFocus -> { onClearFocus() }
        }
    }

    private fun onClickBackButton() {
        currentState.step.currentStep.beforeStep()?.let { beforeStep ->
            reduce { updateStep(currentStep = beforeStep) }
        } // TODO NAVIGATE TO BACK
    }

    private fun onClickNextButton() {
        currentState.step.currentStep.nextStep()?.let { nextStep ->
            reduce { updateStep(currentStep = nextStep) }
        } ?: run {
            /* TODO NEXT */
        }
    }

    private fun onChangeGroupNameTextValue(newText: TextFieldValue) {
        if (newText.text.length > GROUP_NAME_MAX) return
        val isEnabledButton = newText.text.isNotBlank()
        reduce {
            copy(
                groupName = newText,
                enabledStepButton = enabledStepButton.updateStepButton(
                    step.currentStep,
                    isEnabledButton
                )
            )
        }
    }

    private fun onClearGroupName() {
        reduce { copy(groupName = TextFieldValue("")) }
    }

    private fun onClearFocus() {
        postSideEffect { CreationSideEffect.ClearFocus }
    }
}