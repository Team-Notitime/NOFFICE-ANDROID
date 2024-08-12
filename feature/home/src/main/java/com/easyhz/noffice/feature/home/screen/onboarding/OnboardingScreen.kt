package com.easyhz.noffice.feature.home.screen.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.noffice.core.common.util.collectInSideEffectWithLifecycle
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.button.MediumButton
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeBasicScaffold
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Green500
import com.easyhz.noffice.core.design_system.theme.Grey600
import com.easyhz.noffice.core.design_system.theme.Grey900
import com.easyhz.noffice.feature.home.component.onboarding.OnboardingItem
import com.easyhz.noffice.feature.home.contract.onboarding.OnboardingIntent
import com.easyhz.noffice.feature.home.contract.onboarding.OnboardingSideEffect

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    viewModel: OnboardingViewModel = hiltViewModel(),
    navigateToLogin: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val pagerState =
        rememberPagerState(initialPage = 0, pageCount = { uiState.steps.size })
    NofficeBasicScaffold(
        containerColor = Grey900,
        statusBarColor = Grey900,
        navigationBarColor = Grey900,
        bottomBar = {
            MediumButton(
                modifier = Modifier
                    .screenHorizonPadding()
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                text = stringResource(id = if (pagerState.currentPage != uiState.steps.lastIndex) R.string.onboarding_button_next else R.string.onboarding_button_start),
                enabled = true
            ) {
                viewModel.postIntent(OnboardingIntent.ClickButton(pagerState.currentPage))
            }
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(top = 52.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Indicator(
                pagerState = pagerState,
                modifier = Modifier.padding(horizontal = 32.dp)
            )
            HorizontalPager(state = pagerState) {
                OnboardingItem(
                    modifier = Modifier.screenHorizonPadding(),
                    item = uiState.steps[it]
                )
            }
        }
    }

    viewModel.sideEffect.collectInSideEffectWithLifecycle { sideEffect ->
        when(sideEffect) {
            is OnboardingSideEffect.SlideNext -> { pagerState.animateScrollToPage(sideEffect.page) }
            is OnboardingSideEffect.NavigateToLogin -> { navigateToLogin() }
        }
    }
}

@Composable
private fun Indicator(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    activeColor: Color = Green500,
    inactiveColor: Color = Grey600,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        for (i in 0 until pagerState.pageCount) {
            val color = if (i == pagerState.currentPage) activeColor else inactiveColor

            Box(
                modifier = Modifier
                    .size(6.dp)
                    .clip(CircleShape)
                    .background(color)
            )
        }
    }
}