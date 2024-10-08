package com.easyhz.noffice.feature.sign.screen.signUp

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.noffice.core.common.util.collectInSideEffectWithLifecycle
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.loading.LoadingScreenProvider
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeBasicScaffold
import com.easyhz.noffice.core.design_system.component.topBar.DetailTopBar
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.theme.White
import com.easyhz.noffice.core.design_system.util.topBar.DetailTopBarMenu
import com.easyhz.noffice.feature.sign.component.signUp.NameView
import com.easyhz.noffice.feature.sign.component.signUp.TermsDetailBottomSheet
import com.easyhz.noffice.feature.sign.component.signUp.TermsView
import com.easyhz.noffice.feature.sign.contract.login.LoginSideEffect
import com.easyhz.noffice.feature.sign.contract.signUp.SignUpIntent
import com.easyhz.noffice.feature.sign.contract.signUp.SignUpSideEffect
import com.easyhz.noffice.feature.sign.util.signUp.SignUpStep

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    snackBarHostState: SnackbarHostState,
    navigateToUp: () -> Unit,
    navigateToHome: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val context = LocalContext.current
    BackHandler(onBack = {
        viewModel.postIntent(SignUpIntent.ClickBackButton)
    })

    LoadingScreenProvider(
        isLoading = uiState.isLoading
    ) {
        NofficeBasicScaffold(
            statusBarColor = White,
            topBar = {
                DetailTopBar(
                    leadingItem = DetailTopBarMenu(
                        content = {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                painter = painterResource(id = R.drawable.ic_chevron_left),
                                contentDescription = "left",
                                tint = Grey400
                            )
                        },
                        onClick = { viewModel.postIntent(SignUpIntent.ClickBackButton) }
                    )
                )
            }
        ) {
            AnimatedContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                targetState = uiState.step.currentStep,
                transitionSpec = {
                    if (uiState.step.currentStep.ordinal >= (uiState.step.previousStep?.ordinal
                            ?: 0)
                    ) {
                        (slideInHorizontally { width -> width } + fadeIn()).togetherWith(
                            slideOutHorizontally { width -> -width } + fadeOut())
                    } else {
                        (slideInHorizontally { width -> -width } + fadeIn()).togetherWith(
                            slideOutHorizontally { width -> width } + fadeOut())
                    }.using(SizeTransform(clip = false))
                }, label = "signUpFlow"
            ) { targetScreen ->
                when (targetScreen) {
                    SignUpStep.TERMS -> {
                        TermsView(modifier = Modifier.screenHorizonPadding())
                    }

                    SignUpStep.NAME -> {
                        NameView(modifier = Modifier.screenHorizonPadding())
                    }
                }
            }

            if (uiState.isShowTermsBottomSheet) {
                TermsDetailBottomSheet(
                    sheetState = sheetState,
                    termsType = uiState.selectedTerms
                ) { viewModel.postIntent(SignUpIntent.HideTermsBottomSheet) }
            }
        }
    }

    viewModel.sideEffect.collectInSideEffectWithLifecycle { sideEffect ->
        when (sideEffect) {
            is SignUpSideEffect.NavigateToUp -> {
                navigateToUp()
            }
            is SignUpSideEffect.ClearFocus -> {
                focusManager.clearFocus()
            }

            is SignUpSideEffect.NavigateToHome -> {
                navigateToHome()
            }

            is SignUpSideEffect.HideTermsBottomSheet -> {
                sheetState.hide()
                viewModel.postIntent(SignUpIntent.SetTermsBottomSheet(false))
            }
            is SignUpSideEffect.ShowSnackBar -> {
                snackBarHostState.showSnackbar(
                    message = context.getString(sideEffect.stringId),
                    withDismissAction = true
                )
            }
        }
    }
}