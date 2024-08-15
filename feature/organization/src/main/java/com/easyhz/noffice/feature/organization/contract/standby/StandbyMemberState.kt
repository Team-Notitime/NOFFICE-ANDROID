package com.easyhz.noffice.feature.organization.contract.standby

import com.easyhz.noffice.core.common.base.UiState

data class StandbyMemberState(
    val isLoading: Boolean
):UiState() {
    companion object {
        fun init() = StandbyMemberState(
            isLoading = false
        )
    }
}
