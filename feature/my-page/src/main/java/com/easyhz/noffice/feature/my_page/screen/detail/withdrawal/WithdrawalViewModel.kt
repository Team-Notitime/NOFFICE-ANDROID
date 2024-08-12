package com.easyhz.noffice.feature.my_page.screen.detail.withdrawal

import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.feature.my_page.contract.detail.withdrawal.WithdrawalIntent
import com.easyhz.noffice.feature.my_page.contract.detail.withdrawal.WithdrawalSideEffect
import com.easyhz.noffice.feature.my_page.contract.detail.withdrawal.WithdrawalState

class WithdrawalViewModel: BaseViewModel<WithdrawalState, WithdrawalIntent, WithdrawalSideEffect>(
    initialState = WithdrawalState.init()
) {
    override fun handleIntent(intent: WithdrawalIntent) {
        when(intent) {
            is WithdrawalIntent.ClickBackButton -> { onClickBackButton() }
            is WithdrawalIntent.ClickConsentButton -> { onClickConsentButton() }
            is WithdrawalIntent.ClickWithdrawalButton -> { onClickWithdrawalButton() }
        }
    }

    private fun onClickConsentButton() {
        reduce { copy(isChecked = !isChecked) }
    }

    private fun onClickWithdrawalButton() {
        // TODO 탈퇴 처리
        postSideEffect { WithdrawalSideEffect.NavigateToLogIn }
    }

    private fun onClickBackButton() {
        postSideEffect { WithdrawalSideEffect.NavigateToUp }
    }
}