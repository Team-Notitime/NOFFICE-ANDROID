package com.easyhz.noffice.feature.announcement.screen.creation.remind

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.noffice.core.common.util.collectInSideEffectWithLifecycle
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.button.MediumButton
import com.easyhz.noffice.core.design_system.component.calendar.MonthCalendarView
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeBasicScaffold
import com.easyhz.noffice.core.design_system.component.timePicker.NofficeTimePicker
import com.easyhz.noffice.core.design_system.component.topBar.DetailTopBar
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.util.calendar.getCalendarPadding
import com.easyhz.noffice.core.design_system.util.topBar.DetailTopBarMenu
import com.easyhz.noffice.feature.announcement.contract.creation.remind.RemindIntent
import com.easyhz.noffice.feature.announcement.contract.creation.remind.custom.CustomRemindIntent
import com.easyhz.noffice.feature.announcement.contract.creation.remind.custom.CustomRemindSideEffect

@Composable
fun CustomRemindScreen(
    modifier: Modifier = Modifier,
    viewModel: CustomRemindViewModel = hiltViewModel(),
    remindViewModel: RemindViewModel = hiltViewModel(),
    navigateToUp: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val calendarPadding = getCalendarPadding(16, screenWidth).dp

    NofficeBasicScaffold(
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
                    onClick = { viewModel.postIntent(CustomRemindIntent.ClickBackButton) }
                ),
                title = stringResource(id = R.string.announcement_creation_option_custom_remind_create_title),
            )
        },
        bottomBar = {
            MediumButton(
                modifier = Modifier
                    .screenHorizonPadding()
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                text = stringResource(id = R.string.announcement_creation_option_custom_remind_create_button),
                enabled = true
            ) {
                viewModel.postIntent(CustomRemindIntent.ClickSaveButton)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .screenHorizonPadding()
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            MonthCalendarView(
                modifier = Modifier.weight(1f),
                selection = uiState.selectionDate,
                calendarPadding = calendarPadding
            ) {
                viewModel.postIntent(CustomRemindIntent.SelectDate(it))
            }
            NofficeTimePicker(
                modifier = Modifier
                    .weight(0.5f)
                    .align(
                        Alignment.CenterHorizontally
                    ),
                initialTime = uiState.selectionTime
            ) { h, m, isAm ->
                viewModel.postIntent(CustomRemindIntent.ChangeTimeValue(h, m, isAm))
            }
        }
    }

    viewModel.sideEffect.collectInSideEffectWithLifecycle { sideEffect ->
        when(sideEffect) {
            is CustomRemindSideEffect.NavigateToUp -> { navigateToUp() }
            is CustomRemindSideEffect.SaveToDateTime -> {
                remindViewModel.postIntent(RemindIntent.SaveCustomRemind(sideEffect.data))
                navigateToUp()
            }
        }
    }
}