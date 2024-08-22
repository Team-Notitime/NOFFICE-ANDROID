package com.easyhz.noffice.feature.organization.screen.join

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.easyhz.noffice.core.common.util.collectInSideEffectWithLifecycle
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.button.MediumButton
import com.easyhz.noffice.core.design_system.component.image.OrganizationImage
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeBasicScaffold
import com.easyhz.noffice.core.design_system.component.topBar.DetailTopBar
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.theme.Grey500
import com.easyhz.noffice.core.design_system.theme.SubTitle1
import com.easyhz.noffice.core.design_system.theme.Title4
import com.easyhz.noffice.core.design_system.theme.White
import com.easyhz.noffice.core.design_system.util.topBar.DetailTopBarMenu
import com.easyhz.noffice.core.model.organization.OrganizationSignUpInformation
import com.easyhz.noffice.feature.organization.contract.join.JoinIntent
import com.easyhz.noffice.feature.organization.contract.join.JoinSideEffect

@Composable
fun OrganizationJoinScreen(
    modifier: Modifier = Modifier,
    viewModel: OrganizationJoinViewModel = hiltViewModel(),
    organizationSignUpInformation: OrganizationSignUpInformation,
    snackBarHostState: SnackbarHostState,
    navigateToUp: () -> Unit
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        viewModel.postIntent(JoinIntent.InitScreen(organizationSignUpInformation))
    }
    NofficeBasicScaffold(
        statusBarColor = White,
        navigationBarColor = White,
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
                    onClick = { viewModel.postIntent(JoinIntent.ClickBackButton) }
                ),
            )
        },
        bottomBar = {
            MediumButton(
                modifier = Modifier
                    .screenHorizonPadding()
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                text = stringResource(id = R.string.organization_join_button),
            ) {
                viewModel.postIntent(JoinIntent.ClickJoinButton)
            }
        }
    ) {
        Column(
            modifier = modifier
                .padding(it)
                .screenHorizonPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                OrganizationImage(
                    modifier = Modifier.size(120.dp),
                    imageUrl = ""
                )
                Spacer(modifier = Modifier.height(18.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "조지기름",
                        style = Title4
                    )
                    Text(
                        text = stringResource(id = R.string.organization_join_title),
                        style = SubTitle1,
                        color = Grey500
                    )
                }
            }
        }
    }
    viewModel.sideEffect.collectInSideEffectWithLifecycle { sideEffect ->
        when (sideEffect) {
            is JoinSideEffect.NavigateToUp -> {
                navigateToUp()
            }

            is JoinSideEffect.ShowSnackBar -> {
                snackBarHostState.showSnackbar(
                    message = context.getString(sideEffect.stringId),
                    withDismissAction = true
                )
            }
        }
    }
}