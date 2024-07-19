package com.easyhz.noffice.feature.organization.contract.creation

import androidx.compose.ui.text.input.TextFieldValue
import com.easyhz.noffice.core.common.base.UiIntent

sealed class CreationIntent: UiIntent() {
    data object ClickBackButton: CreationIntent()
    data object ClickNextButton: CreationIntent()
    data class ChangeGroupNameTextValue(val text: TextFieldValue): CreationIntent()
    data object ClearGroupName: CreationIntent()
    data object ClearFocus: CreationIntent()
}