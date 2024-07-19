package com.easyhz.noffice.feature.organization.screen.organization

import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.feature.organization.contract.organization.OrganizationIntent
import com.easyhz.noffice.feature.organization.contract.organization.OrganizationSideEffect
import com.easyhz.noffice.feature.organization.contract.organization.OrganizationState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrganizationViewModel @Inject constructor(

): BaseViewModel<OrganizationState, OrganizationIntent, OrganizationSideEffect>(
    initialState = OrganizationState.init()
) {
    override fun handleIntent(intent: OrganizationIntent) {
        when(intent) {
            is OrganizationIntent.ClickOrganizationCreation -> { onClickOrganizationCreation() }
        }
    }

    private fun onClickOrganizationCreation() {
        postSideEffect { OrganizationSideEffect.NavigateToCreation }
    }
}