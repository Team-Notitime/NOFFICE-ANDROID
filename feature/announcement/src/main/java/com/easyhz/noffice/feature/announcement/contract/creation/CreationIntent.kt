package com.easyhz.noffice.feature.announcement.contract.creation

import androidx.compose.ui.text.input.TextFieldValue
import com.easyhz.noffice.core.common.base.UiIntent

sealed class CreationIntent: UiIntent() {
    data object ClickBackButton: CreationIntent()
    data object ClickNextButton: CreationIntent()
    data class SelectedOrganization(val organization: String): CreationIntent()
    data class ChangeTitleTextValue(val newText: String): CreationIntent()
    data class ChangeContentTextValue(val newText: TextFieldValue): CreationIntent()
    data class ClickOptionButton(val option: OptionState): CreationIntent()
}