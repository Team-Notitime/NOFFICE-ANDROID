package com.easyhz.noffice.feature.sign.contract.signUp

import com.easyhz.noffice.core.common.base.UiIntent
import com.easyhz.noffice.feature.sign.util.signUp.Terms

sealed class SignUpIntent: UiIntent() {

    data object ClickTermsAllCheck: SignUpIntent()
    data class ClickTermsCheck(val terms: Terms): SignUpIntent()
    data class ClickTermsDetail(val terms: Terms): SignUpIntent()
}