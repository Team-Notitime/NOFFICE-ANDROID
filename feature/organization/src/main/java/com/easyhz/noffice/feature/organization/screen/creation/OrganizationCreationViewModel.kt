package com.easyhz.noffice.feature.organization.screen.creation

import android.net.Uri
import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.core.common.util.updateStepButton
import com.easyhz.noffice.feature.organization.contract.creation.CreationIntent
import com.easyhz.noffice.feature.organization.contract.creation.CreationSideEffect
import com.easyhz.noffice.feature.organization.contract.creation.CreationState
import com.easyhz.noffice.feature.organization.contract.creation.CreationState.Companion.ORGANIZATION_NAME_MAX
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
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
            is CreationIntent.ChangeOrganizationNameTextValue -> { onChangeOrganizationNameTextValue(intent.text) }
            is CreationIntent.ClearOrganizationName -> { onClearOrganizationName() }
            is CreationIntent.ClearFocus -> { onClearFocus() }
            is CreationIntent.ClickCategoryItem -> { onClickCategoryItem(intent.selectedIndex) }
            is CreationIntent.ClickImageView -> { onClickImageView() }
            is CreationIntent.PickImage -> { onPickImage(intent.uri) }
            is CreationIntent.ChangeEndDate -> { onChangeEndDate(intent.date) }
            is CreationIntent.ChangePromotionTextValue -> { onChangePromotionTextValue(intent.text) }
            is CreationIntent.ClearPromotionCode -> { onClearPromotionCode() }
        }
    }

    private fun onClickBackButton() {
        currentState.step.currentStep.beforeStep()?.let { beforeStep ->
            reduce { updateStep(currentStep = beforeStep) }
        } ?: onNavigateToUp()
    }

    private fun onClickNextButton() {
        currentState.step.currentStep.nextStep()?.let { nextStep ->
            reduce { updateStep(currentStep = nextStep) }
            postSideEffect { CreationSideEffect.ClearFocus }
        } ?: onNavigateToInvitation()
    }

    private fun onChangeOrganizationNameTextValue(newText: String) {
        if (newText.length > ORGANIZATION_NAME_MAX) return
        val isEnabledButton = newText.isNotBlank()
        reduce {
            copy(
                organizationName = newText,
                enabledStepButton = enabledStepButton.updateStepButton(
                    step.currentStep,
                    isEnabledButton
                )
            )
        }
    }

    private fun onClearOrganizationName() {
        reduce { copy(organizationName = "") }
    }

    private fun onClearFocus() {
        postSideEffect { CreationSideEffect.ClearFocus }
    }

    private fun onClickCategoryItem(selectedIndex: Int) {
        reduce { updateCategoryItem(selectedIndex) }
    }

    private fun onClickImageView() {
        if (!currentState.isEnabledGallery) return
        postSideEffect { CreationSideEffect.NavigateToGallery }
        reduce { copy(isEnabledGallery = false) }
    }

    private fun onPickImage(uri: Uri?) {
        reduce { copy(organizationImage = uri ?: Uri.EMPTY, isEnabledGallery = true) }
    }

    private fun onChangeEndDate(date: LocalDate) {
        reduce { copy(endDate = date.takeIf { it != currentState.endDate }) }
    }

    private fun onChangePromotionTextValue(newText: String) {
        reduce { copy(promotionCode = newText) }
    }

    private fun onClearPromotionCode() {
        reduce { copy(promotionCode = "") }
    }

    private fun onNavigateToUp() {
        postSideEffect { CreationSideEffect.NavigateToUp }
    }

    // TODO: 서버 통신 로직 추가
    private fun onNavigateToInvitation() {
        postSideEffect {
            CreationSideEffect.NavigateToInvitation(
                "www.noffice/dhedgyeqi3e83",
                currentState.organizationImage.toString()
            )
        }
    }
}