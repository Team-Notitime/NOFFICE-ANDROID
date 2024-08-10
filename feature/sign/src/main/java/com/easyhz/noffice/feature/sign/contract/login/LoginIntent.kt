package com.easyhz.noffice.feature.sign.contract.login

import android.content.Context
import com.easyhz.noffice.core.common.base.UiIntent

sealed class LoginIntent : UiIntent() {
    data class ClickToLogInWithGoogle(val context: Context): LoginIntent()
}