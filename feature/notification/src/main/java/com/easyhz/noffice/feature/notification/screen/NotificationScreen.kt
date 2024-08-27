package com.easyhz.noffice.feature.notification.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.noffice.core.common.util.collectInSideEffectWithLifecycle
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeBasicScaffold
import com.easyhz.noffice.core.design_system.component.topBar.DetailTopBar
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.theme.Grey50
import com.easyhz.noffice.core.design_system.theme.SemiBold16
import com.easyhz.noffice.core.design_system.util.topBar.DetailTopBarMenu
import com.easyhz.noffice.feature.notification.component.NotificationItem
import com.easyhz.noffice.feature.notification.contract.NotificationIntent
import com.easyhz.noffice.feature.notification.contract.NotificationSideEffect

@Composable
fun NotificationScreen(
    modifier: Modifier = Modifier,
    viewModel: NotificationViewModel = hiltViewModel(),
    navigateToUp: () -> Unit,
    navigateToAnnouncementDetail: (organizationId: Int, announcementId: Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
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
                    onClick = { viewModel.postIntent(NotificationIntent.ClickBackButton) }
                ),
                title = stringResource(id = R.string.notification_title)
            )
        }
    ) { paddingValues ->
        if (uiState.notificationList.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = stringResource(id = R.string.notification_empty),
                    style = SemiBold16
                )
            }
        }
        LazyColumn(
            modifier = modifier
                .padding(paddingValues)
                .screenHorizonPadding(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 12.dp)
        ) {
            items(uiState.notificationList, key = { it.id }) { item ->
                NotificationItem(
                    notification = item
                ) {
                    viewModel.postIntent(NotificationIntent.ClickNotification(item.announcementId, item.organizationId))
                }
            }
        }
    }

    viewModel.sideEffect.collectInSideEffectWithLifecycle { sideEffect ->
        when (sideEffect) {
            is NotificationSideEffect.NavigateToUp -> {
                navigateToUp()
            }
            is NotificationSideEffect.NavigateToAnnouncementDetail -> { navigateToAnnouncementDetail(sideEffect.organizationId, sideEffect.announcementId) }
        }
    }
}