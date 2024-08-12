package com.easyhz.noffice.feature.sign.contract.login

import android.content.Context
import com.easyhz.noffice.core.common.base.UiIntent
import com.easyhz.noffice.feature.sign.util.login.SocialLoginType

sealed class LoginIntent : UiIntent() {
    data class ClickToSocialLogin(val loginType: SocialLoginType, val context: Context): LoginIntent()
}