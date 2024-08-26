package com.easyhz.noffice.feature.my_page.screen

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.noffice.core.common.util.collectInSideEffectWithLifecycle
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.bottomSheet.FloatingBottomSheet
import com.easyhz.noffice.core.design_system.component.bottomSheet.ImageSelectionBottomSheet
import com.easyhz.noffice.core.design_system.component.dialog.MainDialog
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeBasicScaffold
import com.easyhz.noffice.core.design_system.component.textField.MainTextField
import com.easyhz.noffice.core.design_system.component.topBar.DetailTopBar
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.theme.Grey50
import com.easyhz.noffice.core.design_system.theme.Grey800
import com.easyhz.noffice.core.design_system.theme.InputDialogTitle
import com.easyhz.noffice.core.design_system.util.dialog.InputDialogButton
import com.easyhz.noffice.core.design_system.util.terms.TermsType
import com.easyhz.noffice.core.design_system.util.textField.TextFieldIcon
import com.easyhz.noffice.core.design_system.util.topBar.DetailTopBarMenu
import com.easyhz.noffice.feature.my_page.component.ProfileField
import com.easyhz.noffice.feature.my_page.component.SectionItem
import com.easyhz.noffice.feature.my_page.contract.MyPageIntent
import com.easyhz.noffice.feature.my_page.contract.MyPageSideEffect
import com.easyhz.noffice.feature.my_page.contract.menu.MenuIntent
import com.easyhz.noffice.feature.my_page.contract.menu.MenuSideEffect
import com.easyhz.noffice.feature.my_page.util.MyPageSection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPageScreen(
    modifier: Modifier = Modifier,
    viewModel: MyPageViewModel = hiltViewModel(),
    menuViewModel: MyPageMenuViewModel = hiltViewModel(),
    navigateToUp: () -> Unit,
    navigateToTerms: (TermsType) -> Unit,
    navigateToNotice: () -> Unit,
    navigateToConsent: () -> Unit,
    navigateToWithdrawal: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val menuState by menuViewModel.uiState.collectAsStateWithLifecycle()

    val scrollState = rememberScrollState()
    val focusRequester = remember { FocusRequester() }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val galleryLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { viewModel.postIntent(MyPageIntent.PickImage(it)) }
        )

    val cameraLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicture(),
            onResult = { viewModel.postIntent(MyPageIntent.TakePicture(it)) }
        )
    NofficeBasicScaffold(
        containerColor = Grey50,
        statusBarColor = Grey50,
        navigationBarColor = Grey50,
        topBar = {
            DetailTopBar(
                modifier = Modifier.background(Grey50),
                leadingItem = DetailTopBarMenu(
                    content = {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.ic_chevron_left),
                            contentDescription = "left",
                            tint = Grey400
                        )
                    },
                    onClick = { viewModel.postIntent(MyPageIntent.ClickBackButton) }
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .verticalScroll(scrollState)
                .padding(paddingValues)
                .screenHorizonPadding(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ProfileField(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 12.dp),
                name = uiState.user.name,
                email = uiState.user.email,
                imageUrl = uiState.user.profileImageUrl,
                onChangeProfileImage = { viewModel.postIntent(MyPageIntent.ChangeProfileImage) }
            ) {
                viewModel.postIntent(MyPageIntent.ClickUserName)
            }
            MyPageSection.entries.forEach {
                SectionItem(
                    section = it,
                    isChecked = menuState.isCheckedNotification
                ) { menu ->
                    menuViewModel.postIntent(MenuIntent.ClickMenuItem(menu))
                }
            }
        }

        if (uiState.isShowImageBottomSheet) {
            ImageSelectionBottomSheet(
                sheetState = sheetState,
                isEmptyProfile = uiState.user.profileImageUrl.isBlank(),
                onDismissRequest = { viewModel.postIntent(MyPageIntent.HideImageBottomSheet) },
            ) {
                viewModel.postIntent(MyPageIntent.ClickImageBottomSheetItem(it))
            }
        }

        if (uiState.isShowUserNameBottomSheet) {
            FloatingBottomSheet(
                modifier = Modifier.padding(bottom = 32.dp),
                onDismissRequest = { viewModel.postIntent(MyPageIntent.HideUserNameBottomSheet) }
            ) {
                MainTextField(
                    modifier = Modifier.focusRequester(focusRequester),
                    value = uiState.userNameText,
                    onValueChange = { viewModel.postIntent(MyPageIntent.ChangeUserNameText(it)) },
                    title = null,
                    placeholder = stringResource(id = R.string.my_page_menu_user_name_placeholder),
                    isFilled = false,
                    singleLine = true,
                    icon = TextFieldIcon.CLEAR,
                    onClickIcon = { viewModel.postIntent(MyPageIntent.ClearUserNameText) },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = {
                        viewModel.postIntent(MyPageIntent.SaveUserName)
                    })
                )
            }
        }

        if (menuState.isShowSignOutDialog) {
            MainDialog(
                negativeButton = InputDialogButton(
                    stringResource(id = R.string.my_page_menu_sign_out_negative_button_text)
                ) { menuViewModel.postIntent(MenuIntent.ClickSignOutButton(false)) },
                positiveButton = InputDialogButton(
                    stringResource(id = R.string.my_page_menu_sign_out_positive_button_text)
                ) { menuViewModel.postIntent(MenuIntent.ClickSignOutButton(true)) }
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp, horizontal = 4.dp),
                    text = stringResource(id = R.string.my_page_menu_sign_out_dialog_title),
                    style = InputDialogTitle,
                    color = Grey800,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }

    viewModel.sideEffect.collectInSideEffectWithLifecycle { sideEffect ->
        when (sideEffect) {
            is MyPageSideEffect.NavigateToUp -> {
                navigateToUp()
            }

            is MyPageSideEffect.NavigateToGallery -> {
                galleryLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }

            is MyPageSideEffect.NavigateToCamera -> {
                cameraLauncher.launch(sideEffect.uri)
            }

            is MyPageSideEffect.HideBottomSheet -> {
                sheetState.hide()
                viewModel.postIntent(MyPageIntent.CompleteHideBottomSheet)
            }

            is MyPageSideEffect.RequestFocus -> {
                focusRequester.requestFocus()
            }
        }
    }

    menuViewModel.sideEffect.collectInSideEffectWithLifecycle { sideEffect ->
        when(sideEffect) {
            is MenuSideEffect.NavigateToNotice -> { navigateToNotice() }
            is MenuSideEffect.NavigateToServiceOfTerms -> { navigateToTerms(TermsType.SERVICE_OF_TERMS) }
            is MenuSideEffect.NavigateToPrivacyPolicy -> { navigateToTerms(TermsType.PRIVACY_POLICY) }
            is MenuSideEffect.NavigateToConsentToInformation -> { navigateToConsent() }
            is MenuSideEffect.NavigateToWithdrawal -> { navigateToWithdrawal() }
        }
    }
}