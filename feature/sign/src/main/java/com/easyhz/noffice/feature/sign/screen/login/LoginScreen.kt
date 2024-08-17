package com.easyhz.noffice.feature.sign.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.noffice.core.common.util.collectInSideEffectWithLifecycle
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.loading.LoadingScreenProvider
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeBasicScaffold
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.White
import com.easyhz.noffice.feature.sign.component.login.LoginView
import com.easyhz.noffice.feature.sign.contract.login.LoginIntent
import com.easyhz.noffice.feature.sign.contract.login.LoginSideEffect

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
    navigateToHome: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    LoadingScreenProvider(
        isLoading = uiState.isLoading
    ) {
        NofficeBasicScaffold(
            statusBarColor = White
        ) {
            Box(modifier = modifier.fillMaxSize()) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center),
                    painter = painterResource(id = R.drawable.ic_logo_background),
                    contentDescription = "logoBackground",
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    Box(modifier = Modifier.weight(0.8f)) {
                        Image(
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .padding(top = 136.dp),
                            painter = painterResource(id = R.drawable.ic_logo),
                            contentDescription = "logo"
                        )
                    }
                    LoginView(
                        modifier = Modifier
                            .screenHorizonPadding()
                            .weight(0.2f),
                        onClick = {
                            viewModel.postIntent(LoginIntent.ClickToSocialLogin(it, context))
                        }
                    )
                }
            }
        }
    }

    viewModel.sideEffect.collectInSideEffectWithLifecycle { sideEffect ->
        when (sideEffect) {
            is LoginSideEffect.NavigateToHome -> {
                navigateToHome()
            }
             is LoginSideEffect.NavigateToSignUp -> {}
        }
    }
}