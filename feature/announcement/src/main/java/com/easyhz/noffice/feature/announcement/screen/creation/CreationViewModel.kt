package com.easyhz.noffice.feature.announcement.screen.creation

import androidx.compose.ui.text.input.TextFieldValue
import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.feature.announcement.contract.creation.CreationIntent
import com.easyhz.noffice.feature.announcement.contract.creation.CreationSideEffect
import com.easyhz.noffice.feature.announcement.contract.creation.CreationState
import com.easyhz.noffice.feature.announcement.contract.creation.OptionState
import com.easyhz.noffice.feature.announcement.contract.creation.Options
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreationViewModel @Inject constructor(

): BaseViewModel<CreationState, CreationIntent, CreationSideEffect>(
    initialState = CreationState.init()
) {
    override fun handleIntent(intent: CreationIntent) {
        when(intent) {
            is CreationIntent.ClickBackButton -> { onClickBackButton() }
            is CreationIntent.ClickNextButton -> { onClickNextButton() }
            is CreationIntent.SelectedOrganization -> { onSelectedOrganization(organization = intent.organization) }
            is CreationIntent.ChangeTitleTextValue -> { onChangeTitleTextValue(intent.newText) }
            is CreationIntent.ChangeContentTextValue -> { onChangeContentTextValue(intent.newText) }
            is CreationIntent.ClickOptionButton -> { onClickOptionButton(intent.option) }
        }
    }

    private fun onClickBackButton() {
        postSideEffect { CreationSideEffect.NavigateToUp }
    }

    private fun onClickNextButton() {
        postSideEffect { CreationSideEffect.NavigateToNext }
    }

    private fun onSelectedOrganization(organization: String) {
        reduce { copy(selectedOrganization = organization) }
    }

    private fun onChangeTitleTextValue(newText: String) {
        reduce { copy(title = newText) }
    }

    private fun onChangeContentTextValue(newText: TextFieldValue) {
        reduce { copy(content = newText) }
    }

    private fun onClickOptionButton(option: OptionState) {
        when(option.type) {
            Options.DATE_TIME -> { }
            Options.TASK -> { }
            Options.PLACE -> { }
            Options.NOTIFICATION -> { }
        }
    }
}