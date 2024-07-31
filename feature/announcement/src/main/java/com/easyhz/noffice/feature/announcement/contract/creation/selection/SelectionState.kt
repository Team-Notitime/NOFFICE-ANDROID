package com.easyhz.noffice.feature.announcement.contract.creation.selection

import com.easyhz.noffice.core.common.base.UiState

data class SelectionState(
    val enabledButton: Boolean,
    val organizationList: List<String>,
    val selectedOrganization: String,
): UiState() {
    companion object {
        fun init() = SelectionState(
            enabledButton = false,
            organizationList = listOf("나의 동아리", "나의 그룹", "나의 소모임", "나의 스터디"),
            selectedOrganization = "",
        )
    }
}
