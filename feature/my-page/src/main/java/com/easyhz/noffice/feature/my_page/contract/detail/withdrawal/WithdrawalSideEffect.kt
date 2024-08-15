package com.easyhz.noffice.feature.my_page.contract.detail.withdrawal

import com.easyhz.noffice.core.common.base.UiSideEffect

sealed class WithdrawalSideEffect: UiSideEffect() {
    data object NavigateToUp: WithdrawalSideEffect()
    data object NavigateToLogIn: WithdrawalSideEffect()
}