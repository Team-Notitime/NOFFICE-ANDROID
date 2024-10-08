package com.easyhz.noffice.feature.sign.contract.login

import androidx.annotation.StringRes
import com.easyhz.noffice.core.common.base.UiSideEffect

sealed class LoginSideEffect: UiSideEffect() {
    data object NavigateToHome: LoginSideEffect()
    data object NavigateToSignUp: LoginSideEffect()
    data class ShowSnackBar(@StringRes val stringId: Int): LoginSideEffect()
}