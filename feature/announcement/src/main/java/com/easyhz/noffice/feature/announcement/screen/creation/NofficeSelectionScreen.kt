package com.easyhz.noffice.feature.announcement.screen.creation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.noffice.core.common.util.collectInSideEffectWithLifecycle
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.button.CheckButton
import com.easyhz.noffice.core.design_system.component.button.CheckButtonDefaults
import com.easyhz.noffice.core.design_system.component.button.MediumButton
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeBasicScaffold
import com.easyhz.noffice.core.design_system.component.topBar.DetailTopBar
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Green100
import com.easyhz.noffice.core.design_system.theme.Green700
import com.easyhz.noffice.core.design_system.theme.Grey300
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.theme.Grey50
import com.easyhz.noffice.core.design_system.theme.Grey600
import com.easyhz.noffice.core.design_system.util.topBar.DetailTopBarMenu
import com.easyhz.noffice.feature.announcement.component.creation.CreationTitle
import com.easyhz.noffice.feature.announcement.contract.creation.CreationIntent
import com.easyhz.noffice.feature.announcement.contract.creation.CreationSideEffect

@Composable
fun NofficeSelectionScreen(
    modifier: Modifier = Modifier,
    viewModel: CreationViewModel = hiltViewModel(),
    navigateToUp: () -> Unit,
    navigateToAnnouncementCreationContent: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    NofficeBasicScaffold(
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
                ),
                title = stringResource(id = R.string.announcement_creation_title),
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize()
                .screenHorizonPadding(),
        ) {
            CreationTitle(
                title = stringResource(id = R.string.announcement_creation_noffice_selection_title)
            )

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.width(4.dp))
                }
                items(uiState.organizationList) {
                    CheckButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = it,
                        isComplete = uiState.selectedOrganization == it,
                        color = CheckButtonDefaults(
                            completeContainerColor = Green100,
                            completeContentColor = Green700,
                            completeIconColor = Green700,
                            incompleteContainerColor = Grey50,
                            incompleteContentColor = Grey600,
                            incompleteIconColor = Grey300
                        )
                    ) {
                        viewModel.postIntent(CreationIntent.SelectedOrganization(it))
                    }
                }
            }
            MediumButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                text = stringResource(id = R.string.sign_up_button),
                enabled = true
            ) {
                viewModel.postIntent(CreationIntent.ClickNextButton)
            }
        }
    }

    viewModel.sideEffect.collectInSideEffectWithLifecycle {sideEffect ->
        when(sideEffect) {
            is CreationSideEffect.NavigateToUp -> { navigateToUp() }
            is CreationSideEffect.NavigateToNext -> { navigateToAnnouncementCreationContent() }
            else -> {  }
        }
    }
}