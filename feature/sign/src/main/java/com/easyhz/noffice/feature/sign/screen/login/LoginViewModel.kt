package com.easyhz.noffice.feature.sign.screen.login

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.core.model.auth.param.AuthParam
import com.easyhz.noffice.domain.sign.usecase.LoginUseCase
import com.easyhz.noffice.feature.sign.contract.login.LoginIntent
import com.easyhz.noffice.feature.sign.contract.login.LoginSideEffect
import com.easyhz.noffice.feature.sign.contract.login.LoginState
import com.easyhz.noffice.feature.sign.util.login.SocialLoginType
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
            is LoginIntent.ClickToSocialLogin -> { onClickToSocialLogin(intent.loginType, intent.context) }
        }
    }

    private fun onClickToSocialLogin(type: SocialLoginType, context: Context) = viewModelScope.launch {
        loginUseCase.invoke(AuthParam(context, type.name)).onSuccess {
            // FIXME 가입 된 유저 네비게이션 처리
            navigateToHome()
        }.onFailure {
            it.printStackTrace()
        }
    }

    private fun navigateToHome() {
        postSideEffect { LoginSideEffect.NavigateToHome }
    }
}