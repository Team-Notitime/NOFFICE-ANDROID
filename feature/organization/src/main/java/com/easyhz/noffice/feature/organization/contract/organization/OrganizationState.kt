package com.easyhz.noffice.feature.organization.contract.organization

import com.easyhz.noffice.core.common.base.UiState

data class OrganizationState(
    val isRefreshing: Boolean
): UiState() {
    companion object {
        fun init() = OrganizationState(
            isRefreshing = false
        )
    }
}