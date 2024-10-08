package com.easyhz.noffice.feature.organization.screen.management

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.noffice.core.common.util.collectInSideEffectWithLifecycle
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.bottomSheet.ImageSelectionBottomSheet
import com.easyhz.noffice.core.design_system.component.category.CategoryField
import com.easyhz.noffice.core.design_system.component.loading.LoadingScreenProvider
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeBasicScaffold
import com.easyhz.noffice.core.design_system.component.topBar.DetailTopBar
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Green700
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.theme.White
import com.easyhz.noffice.core.design_system.theme.semiBold
import com.easyhz.noffice.core.design_system.util.topBar.DetailTopBarMenu
import com.easyhz.noffice.core.model.organization.OrganizationInformation
import com.easyhz.noffice.feature.organization.component.detail.NumberOfMembersView
import com.easyhz.noffice.feature.organization.component.management.ManagementHeader
import com.easyhz.noffice.feature.organization.component.management.MemberButton
import com.easyhz.noffice.feature.organization.contract.management.ManagementIntent
import com.easyhz.noffice.feature.organization.contract.management.ManagementSideEffect

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrganizationManagementScreen(
    modifier: Modifier = Modifier,
    viewModel: OrganizationManagementViewModel = hiltViewModel(),
    organizationInformation: OrganizationInformation,
    snackBarHostState: SnackbarHostState,
    navigateToUp: () -> Unit,
    navigateToMemberManagement: (Int, String?) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val galleryLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { viewModel.postIntent(ManagementIntent.PickImage(it)) }
        )
    val cameraLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicture(),
            onResult = { viewModel.postIntent(ManagementIntent.TakePicture(it)) }
        )
    LaunchedEffect(Unit) {
        viewModel.postIntent(ManagementIntent.InitScreen(organizationInformation))
    }
    LoadingScreenProvider(
        isLoading = uiState.isSaveLoading
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
                        onClick = { viewModel.postIntent(ManagementIntent.NavigateToUp) }
                    ),
                    trailingItem = DetailTopBarMenu(
                        content = {
                            Text(
                                text = stringResource(id = R.string.organization_management_top_bar_save_button),
                                style = semiBold(18),
                                color = Green700
                            )
                        },
                        onClick = { viewModel.postIntent(ManagementIntent.ClickSaveButton) }
                    ),
                )
            }
        ) { paddingValues ->
            Column(
                modifier = modifier
                    .padding(paddingValues)
                    .screenHorizonPadding(),
            ) {
                ManagementHeader(
                    organizationName = uiState.organizationInformation.name,
                    organizationProfileImage = uiState.selectedImage,
                ) {
                    viewModel.postIntent(ManagementIntent.ClickProfileImage)
                }
                CategoryField(
                    modifier = Modifier.padding(vertical = 24.dp),
                    categoryList = uiState.categoryList
                ) {
                    viewModel.postIntent(ManagementIntent.ClickCategoryItem(it))
                }
                NumberOfMembersView(
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .weight(1f),
                    numberOfMembers = uiState.organizationInformation.members,
                    isLoading = uiState.isLoading
                )
                MemberButton(
                    modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp)
                ) {
                    viewModel.postIntent(ManagementIntent.ClickMemberManagementButton)
                }
            }
            if (uiState.isShowImageBottomSheet) {
                ImageSelectionBottomSheet(
                    isEmptyProfile = (uiState.selectedImage.isNullOrBlank() || uiState.selectedImage == "null"),
                    onDismissRequest = { viewModel.postIntent(ManagementIntent.HideImageBottomSheet) },
                ) {
                    viewModel.postIntent(ManagementIntent.ClickImageBottomSheetItem(it))
                }
            }
        }
    }

    viewModel.sideEffect.collectInSideEffectWithLifecycle { sideEffect ->
        when (sideEffect) {
            is ManagementSideEffect.NavigateToUp -> {
                navigateToUp()
            }
            is ManagementSideEffect.NavigateToMemberManagement -> { navigateToMemberManagement(sideEffect.id, uiState.organizationInformation.profileImageUrl) }
            is ManagementSideEffect.NavigateToCamera -> {
                cameraLauncher.launch(sideEffect.uri)
            }
            is ManagementSideEffect.NavigateToGallery -> {
                galleryLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }
            is ManagementSideEffect.ShowSnackBar -> {
                snackBarHostState.showSnackbar(
                    message = context.getString(sideEffect.stringId),
                    withDismissAction = true
                )
            }
        }
    }
}