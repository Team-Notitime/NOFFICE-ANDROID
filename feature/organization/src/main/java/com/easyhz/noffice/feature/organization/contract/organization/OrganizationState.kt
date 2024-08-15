package com.easyhz.noffice.feature.organization.contract.organization

import com.easyhz.noffice.core.common.base.UiState

data class OrganizationState(
    val organizationList: List<String> // FIXME api 응답값 나오면 변경 하기
): UiState() {
    companion object {
        fun init() = OrganizationState(
            organizationList = listOf("CMC 15th")
        )
    }
}