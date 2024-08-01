package com.easyhz.noffice.feature.announcement.screen.creation.selection

import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.feature.announcement.contract.creation.selection.SelectionIntent
import com.easyhz.noffice.feature.announcement.contract.creation.selection.SelectionSideEffect
import com.easyhz.noffice.feature.announcement.contract.creation.selection.SelectionState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectionViewModel @Inject constructor(

): BaseViewModel<SelectionState, SelectionIntent, SelectionSideEffect>(
    initialState = SelectionState.init()
) {
    override fun handleIntent(intent: SelectionIntent) {
        when(intent) {
            is SelectionIntent.ClickBackButton -> { onClickBackButton() }
            is SelectionIntent.ClickNextButton -> { onClickNextButton() }
            is SelectionIntent.SelectedOrganization -> { onSelectedOrganization(organization = intent.organization) }
        }
    }
    private fun onClickBackButton() {
        postSideEffect { SelectionSideEffect.NavigateToUp }
    }

    private fun onClickNextButton() {
        postSideEffect { SelectionSideEffect.NavigateToNext }
    }

    private fun onSelectedOrganization(organization: String) {
        reduce { copy(selectedOrganization = organization, enabledButton = true) }
    }

}