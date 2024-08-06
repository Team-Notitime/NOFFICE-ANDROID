package com.easyhz.noffice.feature.organization.screen.management

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.noffice.core.common.util.collectInSideEffectWithLifecycle
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.bottomSheet.FloatingBottomSheet
import com.easyhz.noffice.core.design_system.component.category.CategoryField
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeBasicScaffold
import com.easyhz.noffice.core.design_system.component.topBar.DetailTopBar
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Green700
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.theme.White
import com.easyhz.noffice.core.design_system.theme.semiBold
import com.easyhz.noffice.core.design_system.util.topBar.DetailTopBarMenu
import com.easyhz.noffice.core.model.organization.OrganizationInformation
import com.easyhz.noffice.core.model.organization.member.MemberType
import com.easyhz.noffice.feature.organization.component.creation.BottomSheetItems
import com.easyhz.noffice.feature.organization.component.detail.NumberOfMembersView
import com.easyhz.noffice.feature.organization.component.management.ManagementHeader
import com.easyhz.noffice.feature.organization.component.management.MemberButton
import com.easyhz.noffice.feature.organization.contract.management.ManagementIntent
import com.easyhz.noffice.feature.organization.contract.management.ManagementSideEffect
import com.easyhz.noffice.feature.organization.util.creation.BottomSheetItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrganizationManagementScreen(
    modifier: Modifier = Modifier,
    viewModel: OrganizationManagementViewModel = hiltViewModel(),
    organizationInformation: OrganizationInformation,
    numberOfMembers: LinkedHashMap<MemberType, Int>,
    navigateToUp: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
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
        viewModel.postIntent(ManagementIntent.InitScreen(organizationInformation, numberOfMembers))
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
                    onClick = { /* 저장 */ }
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
                categoryList = uiState.category
            ) {
                viewModel.postIntent(ManagementIntent.ClickCategoryItem(it))
            }
            NumberOfMembersView(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .weight(1f),
                numberOfMembers = uiState.numberOfMembers,
                isLoading = uiState.isLoading
            )
            MemberButton(
                modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp)
            ) {
                /* Navigate To Member Management*/
            }
        }
        if (uiState.isShowImageBottomSheet) {
            FloatingBottomSheet(
                modifier = Modifier.padding(bottom = 32.dp),
                roundedCornerShape = RoundedCornerShape(24.dp),
                onDismissRequest = { viewModel.postIntent(ManagementIntent.HideImageBottomSheet) }
            ) {
                BottomSheetItems(
                    bottomSheetItem = enumValues<BottomSheetItem>().copyOfRange(
                        0,
                        if (uiState.selectedImage.isBlank()) 2 else 3
                    ),
                    onClick = { viewModel.postIntent(ManagementIntent.ClickImageBottomSheetItem(it)) }
                )
            }
        }
    }

    viewModel.sideEffect.collectInSideEffectWithLifecycle { sideEffect ->
        when (sideEffect) {
            is ManagementSideEffect.NavigateToUp -> {
                navigateToUp()
            }
            is ManagementSideEffect.NavigateToMemberManagement -> {}
            is ManagementSideEffect.NavigateToCamera -> {
                cameraLauncher.launch(sideEffect.uri)
            }
            is ManagementSideEffect.NavigateToGallery -> {
                galleryLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }
        }
    }
}