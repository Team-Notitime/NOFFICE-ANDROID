package com.easyhz.noffice.feature.sign.contract.signUp

import com.easyhz.noffice.core.common.base.UiSideEffect

sealed class SignUpSideEffect: UiSideEffect() {
    data object ClearFocus: SignUpSideEffect()
    data object NavigateToHome: SignUpSideEffect()
}