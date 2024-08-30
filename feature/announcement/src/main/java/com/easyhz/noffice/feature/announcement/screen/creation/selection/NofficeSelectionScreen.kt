package com.easyhz.noffice.feature.announcement.screen.creation.selection

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.easyhz.noffice.core.common.util.collectInSideEffectWithLifecycle
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.button.CheckButton
import com.easyhz.noffice.core.design_system.component.button.CheckButtonDefaults
import com.easyhz.noffice.core.design_system.component.button.MediumButton
import com.easyhz.noffice.core.design_system.component.dialog.MainDialog
import com.easyhz.noffice.core.design_system.component.exception.ExceptionView
import com.easyhz.noffice.core.design_system.component.loading.LoadingScreenProvider
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeBasicScaffold
import com.easyhz.noffice.core.design_system.component.topBar.DetailTopBar
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Green100
import com.easyhz.noffice.core.design_system.theme.Green700
import com.easyhz.noffice.core.design_system.theme.Grey300
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.theme.Grey50
import com.easyhz.noffice.core.design_system.theme.Grey600
import com.easyhz.noffice.core.design_system.util.dialog.InputDialogButton
import com.easyhz.noffice.core.design_system.util.exception.ExceptionType
import com.easyhz.noffice.core.design_system.util.topBar.DetailTopBarMenu
import com.easyhz.noffice.core.model.organization.member.MemberType
import com.easyhz.noffice.feature.announcement.component.creation.CreationTitle
import com.easyhz.noffice.feature.announcement.contract.creation.selection.SelectionIntent
import com.easyhz.noffice.feature.announcement.contract.creation.selection.SelectionSideEffect

@Composable
fun NofficeSelectionScreen(
    modifier: Modifier = Modifier,
    viewModel: SelectionViewModel = hiltViewModel(),
    navigateToUp: () -> Unit,
    navigateToOrganizationCreation: () -> Unit,
    navigateToAnnouncementCreationContent: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val organizationList = viewModel.organizationState.collectAsLazyPagingItems()
    LoadingScreenProvider(
        isLoading = organizationList.loadState.refresh == LoadState.Loading
    ) {
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
                        onClick = { viewModel.postIntent(SelectionIntent.ClickBackButton) }
                    ),
                    title = stringResource(id = R.string.announcement_creation_title),
                )
            },
            bottomBar = {
                AnimatedVisibility(visible = (organizationList.itemCount != 0 && organizationList.itemSnapshotList.items.any { it.role == MemberType.LEADER })) {
                    MediumButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .screenHorizonPadding()
                            .padding(bottom = 16.dp),
                        text = stringResource(id = R.string.next_button),
                        enabled = uiState.enabledButton
                    ) {
                        viewModel.postIntent(SelectionIntent.ClickNextButton)
                    }
                }
            }
        ) { paddingValues ->
            if((organizationList.itemCount == 0 || organizationList.itemSnapshotList.items.none { it.role == MemberType.LEADER }) && organizationList.loadState.refresh != LoadState.Loading) {
                ExceptionView(
                    modifier = Modifier.fillMaxSize(),
                    type = ExceptionType.NO_ORGANIZATION
                )
            }
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
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(bottom = 16.dp)
                ) {
                    item {
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                    items(organizationList.itemCount, key = { organizationList[it]?.id ?: -1}) { index ->
                        organizationList[index]?.let { item ->
                            if(item.role != MemberType.LEADER) return@items
                            CheckButton(
                                modifier = Modifier.fillMaxWidth(),
                                text = item.name,
                                isComplete = uiState.selectedOrganization == item.id,
                                color = CheckButtonDefaults(
                                    completeContainerColor = Green100,
                                    completeContentColor = Green700,
                                    completeIconColor = Green700,
                                    incompleteContainerColor = Grey50,
                                    incompleteContentColor = Grey600,
                                    incompleteIconColor = Grey300
                                )
                            ) {
                                viewModel.postIntent(SelectionIntent.SelectedOrganization(organizationList[index]?.id ?: -1))
                            }
                        }
                    }
                }
            }
            if (uiState.isShowOrganizationDialog) {
                MainDialog(
                    negativeButton = InputDialogButton(
                        stringResource(id = R.string.home_organization_dialog_negative_button)
                    ) { viewModel.postIntent(SelectionIntent.ClickOrganizationCreation) },
                    positiveButton = InputDialogButton(
                        stringResource(id = R.string.home_organization_dialog_positive_button)
                    ) { viewModel.postIntent(SelectionIntent.ClickPositiveButton) }
                ) {
                    ExceptionView(
                        type = ExceptionType.NO_CREATION
                    )
                }
            }
        }
    }

    viewModel.sideEffect.collectInSideEffectWithLifecycle {sideEffect ->
        when(sideEffect) {
            is SelectionSideEffect.NavigateToUp -> { navigateToUp() }
            is SelectionSideEffect.NavigateToNext -> { navigateToAnnouncementCreationContent(sideEffect.id) }
            is SelectionSideEffect.NavigateToOrganizationCreation -> { navigateToOrganizationCreation() }
        }
    }
}