package com.easyhz.noffice.feature.my_page.screen.detail.withdrawal

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.core.common.error.NofficeError
import com.easyhz.noffice.core.common.util.errorLogging
import com.easyhz.noffice.domain.my_page.usecase.WithdrawUserCase
import com.easyhz.noffice.feature.my_page.contract.detail.withdrawal.WithdrawalIntent
import com.easyhz.noffice.feature.my_page.contract.detail.withdrawal.WithdrawalSideEffect
import com.easyhz.noffice.feature.my_page.contract.detail.withdrawal.WithdrawalState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WithdrawalViewModel @Inject constructor(
    private val withdrawUserCase: WithdrawUserCase
): BaseViewModel<WithdrawalState, WithdrawalIntent, WithdrawalSideEffect>(
    initialState = WithdrawalState.init()
) {
    override fun handleIntent(intent: WithdrawalIntent) {
        when(intent) {
            is WithdrawalIntent.ClickBackButton -> { onClickBackButton() }
            is WithdrawalIntent.ClickConsentButton -> { onClickConsentButton() }
            is WithdrawalIntent.ClickWithdrawalButton -> { onClickWithdrawalButton(intent.context) }
        }
    }

    private fun onClickConsentButton() {
        reduce { copy(isChecked = !isChecked) }
    }

    private fun onClickWithdrawalButton(context: Context) = viewModelScope.launch {
        reduce { copy(isLoading = true) }
        withdrawUserCase.invoke(context).onSuccess {
            navigateToLogIn()
        }.onFailure {
            if (it is NofficeError.NoContent) {
                navigateToLogIn()
            } else {
                errorLogging(this.javaClass.name, "withdraw", it)
            }
        }.also {
            reduce { copy(isLoading = false) }
        }
    }

    private fun navigateToLogIn() {
        postSideEffect { WithdrawalSideEffect.NavigateToLogin }
    }

    private fun onClickBackButton() {
        postSideEffect { WithdrawalSideEffect.NavigateToUp }
    }
}