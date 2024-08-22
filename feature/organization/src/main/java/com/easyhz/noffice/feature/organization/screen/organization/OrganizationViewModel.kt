package com.easyhz.noffice.feature.organization.screen.organization

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.core.design_system.util.topBar.TopBarIconMenu
import com.easyhz.noffice.core.model.organization.Organization
import com.easyhz.noffice.domain.organization.usecase.organization.FetchOrganizationsUseCase
import com.easyhz.noffice.feature.organization.contract.organization.OrganizationIntent
import com.easyhz.noffice.feature.organization.contract.organization.OrganizationSideEffect
import com.easyhz.noffice.feature.organization.contract.organization.OrganizationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrganizationViewModel @Inject constructor(
    private val fetchOrganizationsUseCase: FetchOrganizationsUseCase
) : BaseViewModel<OrganizationState, OrganizationIntent, OrganizationSideEffect>(
    initialState = OrganizationState
) {
    private val _organizationState: MutableStateFlow<PagingData<Organization>> =
        MutableStateFlow(value = PagingData.empty())
    val organizationState: MutableStateFlow<PagingData<Organization>>
        get() = _organizationState
    override fun handleIntent(intent: OrganizationIntent) {
        when (intent) {
            is OrganizationIntent.ClickOrganizationCreation -> {
                onClickOrganizationCreation()
            }

            is OrganizationIntent.ClickOrganization -> {
                onClickOrganization(intent.id, intent.name)
            }

            is OrganizationIntent.ClickTopBarIconMenu -> { onClickTopBarIconMenu(intent.iconMenu) }
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

    private fun onClickTopBarIconMenu(iconMenu: TopBarIconMenu) {
        when(iconMenu) {
            TopBarIconMenu.NOTIFICATION -> { /* TODO 네비게이션 처리 */ }
            TopBarIconMenu.USER -> { navigateToMyPage() }
        }
    }

    private fun onClickOrganizationCreation() {
        postSideEffect { OrganizationSideEffect.NavigateToCreation }
    }

    private fun onClickOrganization(id: Int, name: String) {
        if (id == -1) return
        postSideEffect {
            OrganizationSideEffect.NavigateToDetail(id, name)
        }
    }

    private fun navigateToMyPage() {
        postSideEffect { OrganizationSideEffect.NavigateToMyPage }
    }
}