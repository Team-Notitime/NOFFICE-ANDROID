package com.easyhz.noffice.feature.organization.screen.invitation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.noffice.core.common.util.collectInSideEffectWithLifecycle
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.button.MediumButton
import com.easyhz.noffice.core.design_system.component.image.OrganizationImage
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeBasicScaffold
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Grey100
import com.easyhz.noffice.core.design_system.theme.Grey600
import com.easyhz.noffice.core.design_system.theme.SubTitle1
import com.easyhz.noffice.core.design_system.theme.Title4
import com.easyhz.noffice.feature.organization.component.invitation.UrlView
import com.easyhz.noffice.feature.organization.contract.invitation.InvitationIntent
import com.easyhz.noffice.feature.organization.contract.invitation.InvitationSideEffect

@Composable
fun OrganizationInvitationScreen(
    modifier: Modifier = Modifier,
    viewModel: InvitationViewModel = hiltViewModel(),
    invitationUrl: String,
    imageUrl: String,
    navigateToHome: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val clipboardManager = LocalClipboardManager.current

    LaunchedEffect(Unit) {
        viewModel.postIntent(InvitationIntent.InitScreen(invitationUrl, imageUrl))
    }

    NofficeBasicScaffold {
        Column(
            modifier = modifier
                .screenHorizonPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.weight(0.8f))
                OrganizationImage(
                    modifier = Modifier.size(120.dp),
                    imageUrl = uiState.imageUrl
                )
                Spacer(modifier = Modifier.height(18.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(text = stringResource(id = R.string.organization_creation_success_title), style = Title4)
                    Text(text = stringResource(id = R.string.organization_creation_success_sub_title), style = SubTitle1)
                }
                Spacer(modifier = Modifier.height(18.dp))
                UrlView(
                    modifier = Modifier
                        .padding(horizontal = 32.dp)
                        .fillMaxWidth(),
                    text = uiState.invitationUrl
                ) {
                    viewModel.postIntent(InvitationIntent.ClickCopyUrl)
                }
                Spacer(modifier = Modifier.weight(1f))
            }
            Row(
                modifier = Modifier
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                MediumButton(
                    modifier = Modifier.weight(1f),
                    text = stringResource(id = R.string.organization_creation_success_home_button),
                    contentColor = Grey600,
                    containerColor = Grey100
                ) {
                    viewModel.postIntent(InvitationIntent.ClickHomeButton)
                }
                MediumButton(
                    modifier = Modifier.weight(1f),
                    text = stringResource(id = R.string.organization_creation_success_copy_button)
                ) {
                    viewModel.postIntent(InvitationIntent.ClickCopyUrl)
                }
            }
        }
    }

    viewModel.sideEffect.collectInSideEffectWithLifecycle {sideEffect ->
        when(sideEffect) {
            is InvitationSideEffect.NavigateToHome -> { navigateToHome() }
            is InvitationSideEffect.CopyUrl -> { clipboardManager.setText(AnnotatedString(sideEffect.url)) }
        }
    }
}