package com.easyhz.noffice.feature.sign.contract.login

import com.easyhz.noffice.core.common.base.UiState

data class LoginState(
    val isLoading: Boolean,
    val uid: String,
): UiState() {
    companion object {
        fun init() = LoginState(
            isLoading = false,
            uid = ""
        )
    }
}