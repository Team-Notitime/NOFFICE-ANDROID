package com.easyhz.noffice.feature.organization.contract.creation

import android.net.Uri
import com.easyhz.noffice.core.common.base.UiState
import com.easyhz.noffice.core.common.extension.toEnumMap
import com.easyhz.noffice.core.common.util.Step
import com.easyhz.noffice.core.common.util.toEnabledStepButton
import com.easyhz.noffice.core.model.organization.category.CATEGORY
import com.easyhz.noffice.core.model.organization.category.Category
import com.easyhz.noffice.core.model.organization.category.toState
import com.easyhz.noffice.feature.organization.util.creation.CreationStep
import java.time.LocalDate
import java.util.EnumMap

data class CreationState(
    val step: Step<CreationStep>,
    val enabledStepButton: EnumMap<CreationStep, Boolean>,
    val organizationName: String,
    val category: List<Category>,
    val organizationImage: Uri,
    val endDate: LocalDate?,
    val promotionCode: String,
    val isShowImageBottomSheet: Boolean,
): UiState() {
    companion object {
        const val ORGANIZATION_NAME_MAX = 10
        fun init() = CreationState(
            step = Step(currentStep = CreationStep.ORGANIZATION_NAME, previousStep = null),
            enabledStepButton = CreationStep.entries.toEnabledStepButton(),
            organizationName = "",
            category = CATEGORY.toState(),
            organizationImage = Uri.EMPTY,
            endDate = null,
            promotionCode = "",
            isShowImageBottomSheet = false
        )
    }

    fun CreationState.updateStep(currentStep: CreationStep): CreationState = this.copy(
        step = step.copy(
            previousStep = step.currentStep,
            currentStep = currentStep,
        ),
    )

    fun CreationState.updateCategoryItem(selectedIndex: Int): CreationState {
        val updatedCategory = category.mapIndexed { index, categoryState ->
            categoryState.copy(isSelected = categoryState.isSelected.xor(index == selectedIndex))
        }
        return copy(
            category = updatedCategory,
            enabledStepButton = enabledStepButton.toMutableMap().apply {
                this[step.currentStep] = updatedCategory.any { it.isSelected }
            }.toEnumMap()
        )
    }
}