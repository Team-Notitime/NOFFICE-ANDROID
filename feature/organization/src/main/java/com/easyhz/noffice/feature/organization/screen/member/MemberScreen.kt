package com.easyhz.noffice.feature.organization.screen.member

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.noffice.core.common.util.collectInSideEffectWithLifecycle
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.dialog.MainDialog
import com.easyhz.noffice.core.design_system.component.loading.LoadingScreenProvider
import com.easyhz.noffice.core.design_system.component.member.MemberItem
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeBasicScaffold
import com.easyhz.noffice.core.design_system.component.topBar.DetailTopBar
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.CardExceptionSubTitle
import com.easyhz.noffice.core.design_system.theme.CardExceptionTitle
import com.easyhz.noffice.core.design_system.theme.Green700
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.theme.Grey600
import com.easyhz.noffice.core.design_system.theme.Grey800
import com.easyhz.noffice.core.design_system.theme.SemiBold16
import com.easyhz.noffice.core.design_system.theme.White
import com.easyhz.noffice.core.design_system.theme.semiBold
import com.easyhz.noffice.core.design_system.util.dialog.InputDialogButton
import com.easyhz.noffice.core.design_system.util.topBar.DetailTopBarMenu
import com.easyhz.noffice.feature.organization.component.member.AuthorityBottomSheet
import com.easyhz.noffice.feature.organization.component.member.MemberBottomBar
import com.easyhz.noffice.feature.organization.contract.member.MemberIntent
import com.easyhz.noffice.feature.organization.contract.member.MemberSideEffect
import com.easyhz.noffice.feature.organization.util.member.MemberViewType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemberScreen(
    modifier: Modifier = Modifier,
    viewModel: MemberViewModel = hiltViewModel(),
    snackBarHostState: SnackbarHostState,
    organizationId: Int,
    imageUrl: String?,
    navigateToUp: () -> Unit,
    navigateToInvitation: (String, String, Boolean) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current
    val isEditMode = remember(uiState.viewType) { uiState.viewType == MemberViewType.EDIT }
    LaunchedEffect(key1 = Unit) {
        viewModel.postIntent(MemberIntent.InitScreen(organizationId, imageUrl))
    }
    BackHandler {
        viewModel.postIntent(MemberIntent.ClickBackButton)
    }
    LoadingScreenProvider(
        isLoading = uiState.isLoading,
        statusBarColor = White,
        navigationBarColor = White
    ) {
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
                        onClick = { viewModel.postIntent(MemberIntent.ClickBackButton) }
                    ),
                    title = stringResource(id = uiState.viewType.title),
                    trailingItem = DetailTopBarMenu(
                        content = {
                            AnimatedVisibility(
                                visible = isEditMode,
                                enter = fadeIn(),
                                exit = fadeOut()
                            ) {
                                Text(
                                    text = stringResource(id = R.string.organization_management_top_bar_save_button),
                                    style = semiBold(18),
                                    color = Green700
                                )
                            }
                        },
                        onClick = { viewModel.postIntent(MemberIntent.ClickSaveButton) }
                    ),
                )
            },
            bottomBar = {
                MemberBottomBar(
                    memberViewType = uiState.viewType,
                    onClickListener = object : MemberViewType.ClickListener {
                        override fun onClickLeftButton() {
                            viewModel.postIntent(MemberIntent.ClickLeftButton)
                        }

                        override fun onClickRightButton() {
                            viewModel.postIntent(MemberIntent.ClickRightButton)
                        }
                    }
                )
            }
        ) { paddingValues ->
            if (!uiState.isLoading && uiState.memberList.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = stringResource(id = R.string.organization_management_member_empty),
                        style = SemiBold16,
                        color = Grey600
                    )
                }
            }
            LazyColumn(
                modifier = modifier
                    .padding(paddingValues)
                    .screenHorizonPadding(),
            ) {
                itemsIndexed(uiState.memberList, key = { _, item -> item.id }) { index, item ->
                    MemberItem(
                        modifier = Modifier.padding(vertical = 4.dp),
                        member = item,
                        isEditMode = if(index == 0) false else isEditMode,
                    ) { viewModel.postIntent(MemberIntent.ClickMember(index)) }
                }
            }
            if (uiState.isOpenBottomSheet) {
                AuthorityBottomSheet(
                    modifier = Modifier.screenHorizonPadding(),
                    sheetState = sheetState,
                    memberType = uiState.authorityType,
                    onDismissRequest = { viewModel.postIntent(MemberIntent.HideBottomSheet) },
                    onClickAuthority = {
                        viewModel.postIntent(
                            MemberIntent.ClickAuthorityMemberType(
                                it
                            )
                        )
                    }
                ) {
                    viewModel.postIntent(MemberIntent.ClickAuthorityButton)
                }
            }
        }
        if (uiState.isShowDialog) {
            MainDialog(
                negativeButton = InputDialogButton(
                    stringResource(id = R.string.organization_management_member_dialog_negative_button)
                ) { viewModel.postIntent(MemberIntent.ClickDialogNegativeButton) },
                positiveButton = InputDialogButton(
                    stringResource(id = R.string.organization_management_member_dialog_positive_button)
                ) { viewModel.postIntent(MemberIntent.ClickSaveButton) }
            ) {
                Column(
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = stringResource(id = R.string.organization_management_member_dialog_title),
                        style = CardExceptionTitle,
                        fontSize = 18.sp,
                        color = Grey800
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = stringResource(id = R.string.organization_management_member_dialog_content),
                        style = CardExceptionSubTitle,
                        fontSize = 14.sp,
                    )
                }
            }
        }
    }

    viewModel.sideEffect.collectInSideEffectWithLifecycle { sideEffect ->
        when (sideEffect) {
            is MemberSideEffect.NavigateToUp -> {
                navigateToUp()
            }

            is MemberSideEffect.NavigateToInitiation -> {
                clipboardManager.setText(AnnotatedString(sideEffect.url))
                navigateToInvitation(
                    sideEffect.url,
                    sideEffect.imageUrl,
                    false
                )
            }

            is MemberSideEffect.HideBottomSheet -> {
                sheetState.hide()
                viewModel.postIntent(MemberIntent.CompleteHideBottomSheet)
            }
            is MemberSideEffect.ShowSnackBar -> {
                snackBarHostState.showSnackbar(
                    message = context.getString(sideEffect.stringId),
                    actionLabel = null
                )
            }
        }
    }
}