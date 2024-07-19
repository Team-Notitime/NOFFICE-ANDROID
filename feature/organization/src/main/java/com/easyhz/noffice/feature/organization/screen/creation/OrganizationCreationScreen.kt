package com.easyhz.noffice.feature.organization.screen.creation

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
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.noffice.core.common.util.collectInSideEffectWithLifecycle
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeBasicScaffold
import com.easyhz.noffice.core.design_system.component.topBar.DetailTopBar
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.theme.White
import com.easyhz.noffice.core.design_system.util.topBar.DetailTopBarMenu
import com.easyhz.noffice.feature.organization.component.creation.CategoryView
import com.easyhz.noffice.feature.organization.component.creation.GroupNameView
import com.easyhz.noffice.feature.organization.contract.creation.CreationIntent
import com.easyhz.noffice.feature.organization.contract.creation.CreationSideEffect
import com.easyhz.noffice.feature.organization.util.creation.CreationStep

@Composable
fun OrganizationCreationScreen(
    modifier: Modifier = Modifier,
    viewModel: OrganizationCreationViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current

    BackHandler(onBack = {
        viewModel.postIntent(CreationIntent.ClickBackButton)
    })

    NofficeBasicScaffold(
        modifier = modifier,
        containerColor = White,
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
                    onClick = { viewModel.postIntent(CreationIntent.ClickBackButton) }
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
                if (uiState.step.currentStep.ordinal >= (uiState.step.previousStep?.ordinal ?: 0)) {
                    (slideInHorizontally { width -> width } + fadeIn()).togetherWith(
                        slideOutHorizontally { width -> -width } + fadeOut())
                } else {
                    (slideInHorizontally { width -> -width } + fadeIn()).togetherWith(
                        slideOutHorizontally { width -> width } + fadeOut())
                }.using(SizeTransform(clip = false))
            }, label = "organizationCreationFlow"
        ) { targetScreen ->
            when(targetScreen) {
                CreationStep.GROUP_NAME -> { GroupNameView(modifier = Modifier.screenHorizonPadding()) }
                CreationStep.CATEGORY -> { CategoryView(modifier = Modifier.screenHorizonPadding()) }
                CreationStep.IMAGE -> { }
                CreationStep.END_DATE -> { }
                CreationStep.PROMOTION -> { }
                CreationStep.SUCCESS -> { }
            }
        }
    }

    viewModel.sideEffect.collectInSideEffectWithLifecycle {sideEffect ->
        when(sideEffect) {
            is CreationSideEffect.ClearFocus -> { focusManager.clearFocus() }
        }
    }
}