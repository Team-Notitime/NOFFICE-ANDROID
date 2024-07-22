package com.easyhz.noffice.feature.sign.contract.login

import com.easyhz.noffice.core.common.base.UiIntent

sealed class LoginIntent : UiIntent() {
    data object ClickToLogInWithGoogle: LoginIntent()
}