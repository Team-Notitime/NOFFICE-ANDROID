package com.easyhz.noffice.feature.my_page.contract.menu

import com.easyhz.noffice.core.common.base.UiSideEffect

sealed class MenuSideEffect: UiSideEffect() {
    data object NavigateToNotice: MenuSideEffect()
    data object NavigateToServiceOfTerms: MenuSideEffect()
    data object NavigateToPrivacyPolicy: MenuSideEffect()
    data object NavigateToConsentToInformation: MenuSideEffect()
    data object NavigateToWithdrawal: MenuSideEffect()
}