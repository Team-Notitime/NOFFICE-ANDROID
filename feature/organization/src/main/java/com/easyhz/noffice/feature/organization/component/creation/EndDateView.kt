package com.easyhz.noffice.feature.organization.component.creation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.button.MediumButton
import com.easyhz.noffice.core.design_system.component.calendar.MonthCalendarView
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.util.calendar.getCalendarPadding
import com.easyhz.noffice.feature.organization.contract.creation.CreationIntent
import com.easyhz.noffice.feature.organization.screen.creation.OrganizationCreationViewModel

@Composable
internal fun EndDateView(
    modifier: Modifier = Modifier,
    viewModel: OrganizationCreationViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val calendarPadding = getCalendarPadding(16, screenWidth).dp

    Column(
        modifier = modifier
    ) {
        CommonHeader(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 24.dp),
            title = stringResource(id = R.string.organization_creation_end_date_title)
        )
        MonthCalendarView(
            modifier = Modifier.weight(1f),
            selection = uiState.endDate,
            calendarPadding = calendarPadding
        ) {
            viewModel.postIntent(CreationIntent.ChangeEndDate(it))
        }
        MediumButton(
            modifier = Modifier
                .screenHorizonPadding()
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            text = stringResource(id = R.string.sign_up_button),
            enabled = uiState.enabledStepButton[uiState.step.currentStep] ?: false
        ) {
            viewModel.postIntent(CreationIntent.ClickNextButton)
        }
    }
}