package com.easyhz.noffice.feature.my_page.contract.detail.withdrawal

import com.easyhz.noffice.core.common.base.UiState

data class WithdrawalState(
    val isChecked: Boolean,
    val isLoading: Boolean,
): UiState() {
    companion object {
        fun init() = WithdrawalState(
            isChecked = false,
            isLoading = false
        )
    }
}
