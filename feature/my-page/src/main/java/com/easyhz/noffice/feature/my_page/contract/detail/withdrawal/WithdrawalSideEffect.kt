package com.easyhz.noffice.feature.my_page.contract.detail.withdrawal

import androidx.annotation.StringRes
import com.easyhz.noffice.core.common.base.UiSideEffect

sealed class WithdrawalSideEffect: UiSideEffect() {
    data object NavigateToUp: WithdrawalSideEffect()
    data object NavigateToLogin: WithdrawalSideEffect()
    data class ShowSnackBar(@StringRes val stringId: Int):  WithdrawalSideEffect()
}