package com.easyhz.noffice.feature.my_page.contract.menu

import android.net.Uri
import com.easyhz.noffice.core.common.base.UiSideEffect

sealed class MenuSideEffect: UiSideEffect() {
    data class NavigateToInquiry(val uri: Uri): MenuSideEffect()
    data object NavigateToNotice: MenuSideEffect()
    data object NavigateToServiceOfTerms: MenuSideEffect()
    data object NavigateToPrivacyPolicy: MenuSideEffect()
    data object NavigateToConsentToInformation: MenuSideEffect()
    data object NavigateToWithdrawal: MenuSideEffect()
    data object NavigateToLogin: MenuSideEffect()
}