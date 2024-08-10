package com.easyhz.noffice.feature.sign.screen.login

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.domain.sign.usecase.LoginUseCase
import com.easyhz.noffice.feature.sign.contract.login.LoginIntent
import com.easyhz.noffice.feature.sign.contract.login.LoginSideEffect
import com.easyhz.noffice.feature.sign.contract.login.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
): BaseViewModel<LoginState, LoginIntent, LoginSideEffect>(
    initialState = LoginState.init()
) {
    override fun handleIntent(intent: LoginIntent) {
        when(intent) {
            is LoginIntent.ClickToLogInWithGoogle -> { onClickToLogInWithGoogle(intent.context) }
        }
    }

    private fun onClickToLogInWithGoogle(context: Context) = viewModelScope.launch {
        // TODO 로그인 로직 처리
        loginUseCase.invoke(context).onSuccess {
            println("success")
        }.onFailure {
            it.printStackTrace()
        }
    }

    private fun navigateToHome() {
        postSideEffect { LoginSideEffect.NavigateToHome }
    }
}