package com.easyhz.noffice.feature.announcement.screen.creation.datetime

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.easyhz.noffice.feature.announcement.component.creation.CreationTitle
import com.easyhz.noffice.feature.announcement.contract.creation.CreationIntent
import com.easyhz.noffice.feature.announcement.contract.creation.datetime.DateTimeIntent
import com.easyhz.noffice.feature.announcement.contract.creation.datetime.DateTimeSideEffect
import com.easyhz.noffice.feature.announcement.screen.creation.CreationViewModel

@Composable
fun DateTimeScreen(
    modifier: Modifier = Modifier,
    viewModel: DateTimeViewModel = hiltViewModel(),
    creationViewModel: CreationViewModel = hiltViewModel(),
    date: String?,
    time: String?,
    navigateToUp: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val calendarPadding = getCalendarPadding(16, screenWidth).dp

    LaunchedEffect(key1 = Unit) {
        viewModel.postIntent(DateTimeIntent.InitScreen(date, time))
    }
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
                    onClick = { viewModel.postIntent(DateTimeIntent.ClickBackButton) }
                ),
                title = stringResource(id = R.string.announcement_creation_title),
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .screenHorizonPadding()
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            CreationTitle(
                title = stringResource(id = R.string.announcement_creation_option_date_time_title)
            )
            MonthCalendarView(
                modifier = Modifier.weight(1f),
                selection = uiState.selectionDate,
                calendarPadding = calendarPadding
            ) {
                viewModel.postIntent(DateTimeIntent.SelectDate(it))
            }
            NofficeTimePicker(
                modifier = Modifier
                    .weight(0.5f)
                    .align(
                        Alignment.CenterHorizontally
                    ),
                initialTime = uiState.selectionTime
            ) { h, m, isAm ->
                viewModel.postIntent(DateTimeIntent.ChangeTimeValue(h, m, isAm))
            }
            MediumButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                text = stringResource(id = R.string.announcement_creation_option_button),
                enabled = true
            ) {
                viewModel.postIntent(DateTimeIntent.ClickSaveButton)
            }
        }
    }

    viewModel.sideEffect.collectInSideEffectWithLifecycle { sideEffect ->
        when (sideEffect) {
            is DateTimeSideEffect.NavigateToUp -> {
                navigateToUp()
            }

            is DateTimeSideEffect.NavigateToNext -> {
                creationViewModel.postIntent(CreationIntent.SaveOptionData(sideEffect.data))
                navigateToUp()
            }
        }
    }
}