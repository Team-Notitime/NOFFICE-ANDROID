package com.easyhz.noffice.feature.announcement.screen.detail

import android.content.Intent
import android.net.Uri
import android.webkit.WebView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.noffice.core.common.util.collectInSideEffectWithLifecycle
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.bottomSheet.BottomSheet
import com.easyhz.noffice.core.design_system.component.button.MediumButton
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeBasicScaffold
import com.easyhz.noffice.core.design_system.component.topBar.DetailTopBar
import com.easyhz.noffice.core.design_system.extension.noRippleClickable
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Blue600
import com.easyhz.noffice.core.design_system.theme.Green500
import com.easyhz.noffice.core.design_system.theme.Grey200
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.theme.Grey50
import com.easyhz.noffice.core.design_system.theme.White
import com.easyhz.noffice.core.design_system.util.topBar.DetailTopBarMenu
import com.easyhz.noffice.core.model.organization.member.MemberType
import com.easyhz.noffice.feature.announcement.component.detail.PlaceBottomSheetTopBar
import com.easyhz.noffice.feature.announcement.component.detail.PlaceWebView
import com.easyhz.noffice.feature.announcement.component.detail.ReaderBottomSheet
import com.easyhz.noffice.feature.announcement.component.detail.contentField
import com.easyhz.noffice.feature.announcement.component.detail.detailField
import com.easyhz.noffice.feature.announcement.component.detail.detailTitle
import com.easyhz.noffice.feature.announcement.component.detail.organizationField
import com.easyhz.noffice.feature.announcement.component.detail.taskListField
import com.easyhz.noffice.feature.announcement.contract.detail.DetailIntent
import com.easyhz.noffice.feature.announcement.contract.detail.DetailSideEffect
import com.easyhz.noffice.feature.announcement.util.detail.DetailType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnnouncementDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: AnnouncementDetailViewModel = hiltViewModel(),
    organizationId: Int,
    id: Int,
    title: String,
    navigateToUp: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val clipBoardManager = LocalClipboardManager.current
    val context = LocalContext.current
    val webView = remember { WebView(context) }
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState()
    )

    LaunchedEffect(Unit) {
        viewModel.postIntent(DetailIntent.InitScreen(organizationId, id, title))
    }
    BottomSheetScaffold(
        sheetContainerColor = White,
        sheetContentColor = Color.Transparent,
        sheetDragHandle = null,
        sheetPeekHeight = if (uiState.organizationInformation.role == MemberType.LEADER) 68.dp else 0.dp,
        scaffoldState = scaffoldState,
        sheetContent = {
            if (uiState.organizationInformation.role == MemberType.LEADER) {
                ReaderBottomSheet(
                    readerList = uiState.readerList,
                    nonReaderList = uiState.nonReaderList,
                    selectedReaderType = uiState.selectedReaderType
                ) {
                    viewModel.postIntent(
                        DetailIntent.ClickReaderType(
                            it,
                            scaffoldState.bottomSheetState.currentValue == SheetValue.Expanded
                        )
                    )
                }
            }
        },
        content = {
            NofficeBasicScaffold(
                containerColor = Grey50,
                statusBarColor = Grey50,
                navigationBarColor = Grey50,
                topBar = {
                    DetailTopBar(
                        modifier = Modifier
                            .background(Grey50)
                            .then(
                                if (scaffoldState.bottomSheetState.currentValue == SheetValue.Expanded) Modifier.noRippleClickable {
                                    viewModel.postIntent(DetailIntent.PartialExpandBottomSheet)
                                } else Modifier
                            ),
                        leadingItem = DetailTopBarMenu(
                            content = {
                                Icon(
                                    modifier = Modifier.size(24.dp),
                                    painter = painterResource(id = R.drawable.ic_chevron_left),
                                    contentDescription = "left",
                                    tint = Grey400
                                )
                            },
                            onClick = {
                                if (scaffoldState.bottomSheetState.currentValue == SheetValue.Expanded) {
                                    viewModel.postIntent(DetailIntent.PartialExpandBottomSheet)
                                } else {
                                    viewModel.postIntent(DetailIntent.NavigateToUp)
                                }
                            }
                        ),
                    )
                }
            ) { paddingValues ->
                LazyColumn(
                    modifier = modifier
                        .padding(paddingValues)
                        .padding(horizontal = 24.dp)
                ) {
                    detailTitle(
                        title = uiState.announcement.title,
                        date = uiState.announcement.createdAt,
                        isLoading = uiState.isLoading
                    )
                    item {
                        HorizontalDivider(
                            modifier = Modifier.fillMaxWidth(),
                            thickness = 1.dp,
                            color = Grey200
                        )
                    }
                    organizationField(
                        modifier = Modifier.padding(vertical = 12.dp),
                        organizationName = uiState.organizationInformation.name,
                        profileImage = uiState.organizationInformation.profileImageUrl,
                        category = uiState.organizationInformation.category.takeIf { it.isNotEmpty() }
                            ?.joinToString(separator = "·") ?: "",
                        isLoading = uiState.isLoading
                    )

                    uiState.announcement.endAt?.let {
                        detailField(
                            detailType = DetailType.DATE_TIME,
                            value = it,
                            isLoading = uiState.isLoading
                        ) { }
                    }
                    uiState.announcement.placeLinkName?.let {
                        item {
                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(12.dp)
                            )
                        }
                        detailField(
                            detailType = DetailType.PLACE,
                            value = it,
                            isLoading = uiState.isLoading
                        ) { viewModel.postIntent(DetailIntent.ClickPlace) }
                    }

                    contentField(
                        modifier = Modifier.padding(vertical = 16.dp),
                        content = uiState.announcement.content,
                        isLoading = uiState.isLoading
                    )

                    taskListField(
                        taskList = uiState.taskList
                    ) {
                        viewModel.postIntent(DetailIntent.CheckTask(it))
                    }
                    item {
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(16.dp)
                        )
                    }
                }
                if (uiState.isShowBottomSheet) {
                    BottomSheet(
                        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                        containerColor = White,
                        onDismissRequest = {
                            viewModel.postIntent(DetailIntent.HideBottomSheet)
                        },
                        dragHandle = {
                            PlaceBottomSheetTopBar(
                                placeUrl = uiState.announcement.placeLinkUrl ?: "",
                                onClickUrl = { viewModel.postIntent(DetailIntent.CopyUrl) }
                            ) {
                                viewModel.postIntent(DetailIntent.ClickWebViewBack)
                            }
                        }
                    ) {
                        Box(
                            modifier = Modifier.fillMaxHeight(0.9f),
                            contentAlignment = Alignment.Center
                        ) {
                            PlaceWebView(
                                modifier = Modifier.fillMaxSize(),
                                url = uiState.announcement.placeLinkUrl ?: "",
                                webView = webView,
                                onGoBack = {
                                    viewModel.postIntent(DetailIntent.UpdateCanGoBack(it))
                                }
                            ) {
                                viewModel.postIntent(DetailIntent.LoadWebView(it))
                            }
                            MediumButton(
                                modifier = Modifier
                                    .screenHorizonPadding()
                                    .fillMaxWidth()
                                    .align(Alignment.BottomCenter)
                                    .padding(bottom = 32.dp),
                                text = stringResource(id = R.string.announcement_detail_web_view_browser_button),
                                containerColor = Blue600,
                                contentColor = White,
                                onClick = { viewModel.postIntent(DetailIntent.ClickOpenBrowser) }
                            )
                            if (uiState.isWebViewLoading) {
                                CircularProgressIndicator(
                                    strokeWidth = 3.dp,
                                    color = Green500
                                )
                            }
                        }
                    }
                }
            }
        },
    )

    viewModel.sideEffect.collectInSideEffectWithLifecycle { sideEffect ->
        when (sideEffect) {
            is DetailSideEffect.NavigateToUp -> {
                navigateToUp()
            }

            is DetailSideEffect.OpenBrowser -> {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(sideEffect.url))
                context.startActivity(intent)
            }

            is DetailSideEffect.CopyUrl -> {
                clipBoardManager.setText(AnnotatedString(sideEffect.url))
            }

            is DetailSideEffect.NavigateToUpInWebView -> {
                webView.goBack()
            }

            is DetailSideEffect.PartialExpandBottomSheet -> {
                scaffoldState.bottomSheetState.partialExpand()
            }

            is DetailSideEffect.ExpandBottomSheet -> {
                scaffoldState.bottomSheetState.expand()
            }
        }
    }
}