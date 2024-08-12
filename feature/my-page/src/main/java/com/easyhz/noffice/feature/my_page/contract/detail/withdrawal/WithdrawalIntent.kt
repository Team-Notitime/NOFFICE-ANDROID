package com.easyhz.noffice.feature.my_page.contract.detail.withdrawal

import com.easyhz.noffice.core.common.base.UiIntent

sealed class WithdrawalIntent: UiIntent() {
    data object ClickBackButton: WithdrawalIntent()
    data object ClickConsentButton: WithdrawalIntent()
    data object ClickWithdrawalButton: WithdrawalIntent()
}