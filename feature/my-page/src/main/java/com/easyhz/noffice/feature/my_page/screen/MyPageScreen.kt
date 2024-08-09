package com.easyhz.noffice.feature.my_page.screen

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.noffice.core.common.util.collectInSideEffectWithLifecycle
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.bottomSheet.ImageSelectionBottomSheet
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeBasicScaffold
import com.easyhz.noffice.core.design_system.component.topBar.DetailTopBar
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.theme.Grey50
import com.easyhz.noffice.core.design_system.util.topBar.DetailTopBarMenu
import com.easyhz.noffice.feature.my_page.component.ProfileField
import com.easyhz.noffice.feature.my_page.component.SectionItem
import com.easyhz.noffice.feature.my_page.contract.MyPageIntent
import com.easyhz.noffice.feature.my_page.contract.MyPageSideEffect
import com.easyhz.noffice.feature.my_page.util.MyPageSection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPageScreen(
    modifier: Modifier = Modifier,
    viewModel: MyPageViewModel = hiltViewModel(),
    navigateToUp: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()
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
                viewModel.postIntent(MyPageIntent.ChangeUserName)
            }
            MyPageSection.entries.forEach {
                SectionItem(
                    section = it,
                    isChecked = uiState.isCheckedNotification
                ) { menu ->
                    viewModel.postIntent(MyPageIntent.ClickMenuItem(menu))
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
    }

    viewModel.sideEffect.collectInSideEffectWithLifecycle { sideEffect ->
        when(sideEffect) {
            is MyPageSideEffect.NavigateToUp -> { navigateToUp() }
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
        }
    }
}