package com.easyhz.noffice.feature.announcement.screen.detail

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeBasicScaffold
import com.easyhz.noffice.core.design_system.component.topBar.DetailTopBar
import com.easyhz.noffice.core.design_system.theme.Grey200
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.theme.Grey50
import com.easyhz.noffice.core.design_system.util.topBar.DetailTopBarMenu
import com.easyhz.noffice.feature.announcement.component.detail.ContentField
import com.easyhz.noffice.feature.announcement.component.detail.DetailField
import com.easyhz.noffice.feature.announcement.component.detail.DetailTitle
import com.easyhz.noffice.feature.announcement.component.detail.OrganizationField
import com.easyhz.noffice.feature.announcement.contract.detail.DetailIntent
import com.easyhz.noffice.feature.announcement.util.detail.DetailType

@Composable
fun AnnouncementDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: AnnouncementDetailViewModel = hiltViewModel(),
    id: Int,
    title: String,
    navigateToUp: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.postIntent(DetailIntent.InitScreen(id, title))
    }
    NofficeBasicScaffold(
        containerColor = Grey50,
        statusBarColor = Grey50,
        navigationBarColor = Grey50,
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
                    onClick = { }
                ),
            )
        }
    ) {
        LazyColumn(
            modifier = modifier
                .padding(it)
                .padding(horizontal = 24.dp)
        ) {
            item {
                DetailTitle(
                    title = uiState.detail.title,
                    date = uiState.detail.creationDate,
                    isLoading = uiState.isLoading
                )
            }
            item {
                Divider(
                    modifier = Modifier.fillMaxWidth(),
                    color = Grey200,
                    thickness = 1.dp
                )
            }
            item {
                OrganizationField(
                    modifier = Modifier.padding(vertical = 12.dp),
                    organizationName = uiState.detail.organizationName,
                    profileImage = uiState.detail.organizationProfileImage,
                    category = uiState.detail.organizationCategory,
                    isLoading = uiState.isLoading
                )
            }
            item {
                DetailField(
                    detailType = DetailType.DATE_TIME,
                    value = uiState.detail.date,
                    isLoading = uiState.isLoading
                ) { }
            }
            item {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(12.dp)
                )
            }
            item {
                DetailField(
                    detailType = DetailType.PLACE,
                    value = uiState.detail.place,
                    isLoading = uiState.isLoading
                ) { }
            }
            item {
                ContentField(
                    modifier = Modifier.padding(vertical = 16.dp),
                    content = uiState.detail.content,
                    isLoading = uiState.isLoading
                )
            }
//            items(uiState.detail.taskList) {
//
//            }
        }
    }
}