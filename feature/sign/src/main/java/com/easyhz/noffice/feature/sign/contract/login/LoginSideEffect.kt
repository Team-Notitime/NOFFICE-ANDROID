package com.easyhz.noffice.feature.sign.contract.login

import com.easyhz.noffice.core.common.base.UiSideEffect

sealed class LoginSideEffect: UiSideEffect() {
    data object NavigateToHome: LoginSideEffect()
    data class NavigateToSignUp(val token: String): LoginSideEffect()
}