package com.easyhz.noffice.feature.announcement.screen.detail

import android.content.Intent
import android.net.Uri
import android.webkit.WebView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.noffice.core.common.util.collectInSideEffectWithLifecycle
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.bottomSheet.BottomSheet
import com.easyhz.noffice.core.design_system.component.button.MediumButton
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeBasicScaffold
import com.easyhz.noffice.core.design_system.component.topBar.DetailTopBar
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Blue600
import com.easyhz.noffice.core.design_system.theme.Green500
import com.easyhz.noffice.core.design_system.theme.Grey200
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.theme.Grey50
import com.easyhz.noffice.core.design_system.theme.White
import com.easyhz.noffice.core.design_system.util.topBar.DetailTopBarMenu
import com.easyhz.noffice.feature.announcement.component.detail.ContentField
import com.easyhz.noffice.feature.announcement.component.detail.DetailField
import com.easyhz.noffice.feature.announcement.component.detail.DetailTitle
import com.easyhz.noffice.feature.announcement.component.detail.OrganizationField
import com.easyhz.noffice.feature.announcement.component.detail.PlaceBottomSheetTopBar
import com.easyhz.noffice.feature.announcement.component.detail.PlaceWebView
import com.easyhz.noffice.feature.announcement.component.detail.TaskListField
import com.easyhz.noffice.feature.announcement.contract.detail.DetailIntent
import com.easyhz.noffice.feature.announcement.contract.detail.DetailSideEffect
import com.easyhz.noffice.feature.announcement.util.detail.DetailType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnnouncementDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: AnnouncementDetailViewModel = hiltViewModel(),
    id: Int,
    title: String,
    navigateToUp: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val webView = remember { WebView(context) }
    LaunchedEffect(Unit) {
        viewModel.postIntent(DetailIntent.InitScreen(id, title))
    }
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
                    onClick = { viewModel.postIntent(DetailIntent.NavigateToUp) }
                ),
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .verticalScroll(scrollState)
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .padding(bottom = 16.dp)
        ) {
            DetailTitle(
                title = uiState.detail.title,
                date = uiState.detail.creationDate,
                isLoading = uiState.isLoading
            )
            Divider(
                modifier = Modifier.fillMaxWidth(),
                color = Grey200,
                thickness = 1.dp
            )
            OrganizationField(
                modifier = Modifier.padding(vertical = 12.dp),
                organizationName = uiState.detail.organizationName,
                profileImage = uiState.detail.organizationProfileImage,
                category = uiState.detail.organizationCategory,
                isLoading = uiState.isLoading
            )

            DetailField(
                detailType = DetailType.DATE_TIME,
                value = uiState.detail.date,
                isLoading = uiState.isLoading
            ) { }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(12.dp)
            )

            DetailField(
                detailType = DetailType.PLACE,
                value = uiState.detail.place,
                isLoading = uiState.isLoading
            ) { viewModel.postIntent(DetailIntent.ClickPlace) }

            ContentField(
                modifier = Modifier.padding(vertical = 16.dp),
                content = uiState.detail.content,
                isLoading = uiState.isLoading
            )
            TaskListField(
                taskList = uiState.detail.taskList,
                isLoading = uiState.isLoading
            ) {
                println("click task list >> $it")
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
                        placeUrl = uiState.detail.placeUrl,
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
                        url = uiState.detail.placeUrl,
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

    viewModel.sideEffect.collectInSideEffectWithLifecycle { sideEffect ->
        when (sideEffect) {
            is DetailSideEffect.NavigateToUp -> { navigateToUp() }
            is DetailSideEffect.OpenBrowser -> {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(sideEffect.url))
                context.startActivity(intent)
            }
            is DetailSideEffect.NavigateToUpInWebView -> {
                webView.goBack()
            }
        }
    }
}