package com.easyhz.noffice.feature.my_page.contract.menu

import com.easyhz.noffice.core.common.base.UiState

data class MenuState(
    val isShowSignOutDialog: Boolean,
    val isCheckedNotification: Boolean,
): UiState() {
    companion object {
        fun init() = MenuState(
            isShowSignOutDialog = false,
            isCheckedNotification = true
        )
    }
}
