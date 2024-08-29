package com.easyhz.noffice.feature.my_page.contract.menu

import com.easyhz.noffice.core.common.base.UiState

data class MenuState(
    val isShowLogoutDialog: Boolean,
    val isCheckedNotification: Boolean,
    val isLoading: Boolean
): UiState() {
    companion object {
        fun init() = MenuState(
            isShowLogoutDialog = false,
            isCheckedNotification = true,
            isLoading = false
        )
    }
}
