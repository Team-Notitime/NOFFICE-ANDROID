package com.easyhz.noffice.feature.announcement.contract.creation.selection

import com.easyhz.noffice.core.common.base.UiState

data class SelectionState(
    val enabledButton: Boolean,
    val selectedOrganization: Int,
    val isShowOrganizationDialog: Boolean,
): UiState() {
    companion object {
        fun init() = SelectionState(
            enabledButton = false,
            selectedOrganization = -1,
            isShowOrganizationDialog = false
        )
    }
}
