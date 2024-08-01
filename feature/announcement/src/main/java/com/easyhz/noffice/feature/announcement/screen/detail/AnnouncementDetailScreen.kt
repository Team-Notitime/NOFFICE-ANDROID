package com.easyhz.noffice.feature.announcement.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.bottomSheet.BottomSheet
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeBasicScaffold
import com.easyhz.noffice.core.design_system.component.topBar.DetailTopBar
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
import com.easyhz.noffice.feature.announcement.component.detail.PlaceWebView
import com.easyhz.noffice.feature.announcement.component.detail.TaskListField
import com.easyhz.noffice.feature.announcement.contract.detail.DetailIntent
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
                    onClick = { }
                ),
            )
        }
    ) {
        Column(
            modifier = modifier
                .verticalScroll(scrollState)
                .padding(it)
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
                containerColor = White,
                onDismissRequest = { viewModel.postIntent(DetailIntent.HideBottomSheet) }
            ) {
                Box(
                    modifier = Modifier.fillMaxHeight(0.9f),
                    contentAlignment = Alignment.Center
                ) {
                    PlaceWebView(
                        url = uiState.detail.placeUrl
                    ) {
                        viewModel.postIntent(DetailIntent.LoadWebView(it))
                    }
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
}