package com.easyhz.noffice.feature.my_page.contract.detail.withdrawal

import android.content.Context
import com.easyhz.noffice.core.common.base.UiIntent

sealed class WithdrawalIntent: UiIntent() {
    data object ClickBackButton: WithdrawalIntent()
    data object ClickConsentButton: WithdrawalIntent()
    data class ClickWithdrawalButton(val context: Context): WithdrawalIntent()
}