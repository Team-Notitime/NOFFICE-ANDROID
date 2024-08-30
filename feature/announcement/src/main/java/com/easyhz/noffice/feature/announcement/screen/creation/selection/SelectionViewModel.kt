package com.easyhz.noffice.feature.announcement.screen.creation.selection

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.core.model.organization.Organization
import com.easyhz.noffice.domain.organization.usecase.organization.FetchOrganizationsUseCase
import com.easyhz.noffice.feature.announcement.contract.creation.selection.SelectionIntent
import com.easyhz.noffice.feature.announcement.contract.creation.selection.SelectionSideEffect
import com.easyhz.noffice.feature.announcement.contract.creation.selection.SelectionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectionViewModel @Inject constructor(
    private val fetchOrganizationsUseCase: FetchOrganizationsUseCase
): BaseViewModel<SelectionState, SelectionIntent, SelectionSideEffect>(
    initialState = SelectionState.init()
) {
    private val _organizationState: MutableStateFlow<PagingData<Organization>> =
        MutableStateFlow(value = PagingData.empty())
    val organizationState: MutableStateFlow<PagingData<Organization>>
        get() = _organizationState

    override fun handleIntent(intent: SelectionIntent) {
        when(intent) {
            is SelectionIntent.ClickBackButton -> { onClickBackButton() }
            is SelectionIntent.ClickNextButton -> { onClickNextButton() }
            is SelectionIntent.ClickPositiveButton -> { onClickPositiveButton() }
            is SelectionIntent.SelectedOrganization -> { onSelectedOrganization(organizationId = intent.id) }
            is SelectionIntent.ShowOrganizationCreationDialog -> { setOrganizationCreationDialog(true) }
            is SelectionIntent.HideOrganizationCreationDialog -> { setOrganizationCreationDialog(false) }
            is SelectionIntent.ClickOrganizationCreation -> { onClickOrganizationCreation() }
        }
    }
    init {
        fetchOrganizations()
    }

    private fun fetchOrganizations() = viewModelScope.launch {
        fetchOrganizationsUseCase.invoke()
            .distinctUntilChanged()
            .cachedIn(viewModelScope).collectLatest {
                _organizationState.value = it
            }
    }

    private fun onClickBackButton() {
        postSideEffect { SelectionSideEffect.NavigateToUp }
    }

    private fun onClickNextButton() {
        postSideEffect { SelectionSideEffect.NavigateToNext(currentState.selectedOrganization) }
    }

    private fun onSelectedOrganization(organizationId: Int) {
        reduce { copy(selectedOrganization = organizationId, enabledButton = true) }
    }

    private fun setOrganizationCreationDialog(value: Boolean) {
        reduce { copy(isShowOrganizationDialog = value) }
    }

    private fun onClickOrganizationCreation() {
        setOrganizationCreationDialog(false)
        postSideEffect { SelectionSideEffect.NavigateToOrganizationCreation }
    }

    private fun onClickPositiveButton() {
        setOrganizationCreationDialog(false)
        onClickBackButton()
    }

}