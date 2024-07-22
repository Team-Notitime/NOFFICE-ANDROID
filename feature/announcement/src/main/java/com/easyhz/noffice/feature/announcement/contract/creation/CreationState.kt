package com.easyhz.noffice.feature.announcement.contract.creation

import com.easyhz.noffice.core.common.base.UiState

data class CreationState(
    val organizationList: List<String>,
    val selectedOrganization: String,
    val healthCheck: Int,
    val title: String,
    val content: String,
    val taskList: List<String>
): UiState() {
    companion object {
        fun init() = CreationState(
            organizationList = listOf("나의 동아리", "나의 그룹", "나의 소모임", "나의 스터디"),
            selectedOrganization = "",
            healthCheck = 0,
            title = "",
            content = "",
            taskList = emptyList()
        )
    }
}
