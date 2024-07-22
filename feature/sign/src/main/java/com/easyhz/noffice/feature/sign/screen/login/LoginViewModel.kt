package com.easyhz.noffice.feature.sign.screen.login

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.feature.sign.contract.login.LoginIntent
import com.easyhz.noffice.feature.sign.contract.login.LoginSideEffect
import com.easyhz.noffice.feature.sign.contract.login.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    @ApplicationContext private val context: Context
): BaseViewModel<LoginState, LoginIntent, LoginSideEffect>(
    initialState = LoginState.init()
) {
    override fun handleIntent(intent: LoginIntent) {
        when(intent) {
            is LoginIntent.ClickToLogInWithGoogle -> { onClickToLogInWithGoogle() }
        }
    }

    private fun onClickToLogInWithGoogle() = viewModelScope.launch {
        // TODO 로그인 로직 처리
        postSideEffect { LoginSideEffect.NavigateToHome }
    }
}