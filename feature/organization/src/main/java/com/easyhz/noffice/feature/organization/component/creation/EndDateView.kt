package com.easyhz.noffice.feature.organization.component.creation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.button.MediumButton
import com.easyhz.noffice.core.design_system.component.calendar.MonthCalendarView
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Green100
import com.easyhz.noffice.core.design_system.util.calendar.getCalendarPadding
import com.easyhz.noffice.feature.organization.contract.creation.CreationIntent
import com.easyhz.noffice.feature.organization.screen.creation.OrganizationCreationViewModel
import com.easyhz.noffice.feature.organization.util.creation.CreationStep
import com.easyhz.noffice.feature.organization.util.creation.getCaption
import com.easyhz.noffice.feature.organization.util.creation.rememberEndString

@Composable
internal fun EndDateView(
    modifier: Modifier = Modifier,
    viewModel: OrganizationCreationViewModel = hiltViewModel(),
    creationStep: CreationStep
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val calendarPadding = getCalendarPadding(16, screenWidth).dp
    val caption = stringResource(id = getCaption(uiState.endDate))
    val annotatedString = rememberEndString(uiState.endDate, caption)

    Column(
        modifier = modifier
    ) {
        CommonHeader(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 24.dp),
            creationStep = creationStep
        )
        MonthCalendarView(
            modifier = Modifier.weight(1f),
            selection = uiState.endDate,
            calendarPadding = calendarPadding
        ) {
            viewModel.postIntent(CreationIntent.ChangeEndDate(it))
        }
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(Green100)
                .heightIn(min = 52.dp)
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center),
                text = annotatedString,
                textAlign = TextAlign.Center
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.2f)
        )
        MediumButton(
            modifier = Modifier
                .screenHorizonPadding()
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            text = stringResource(id = R.string.next_button),
            enabled = uiState.enabledStepButton[uiState.step.currentStep] ?: false
        ) {
            viewModel.postIntent(CreationIntent.ClickNextButton)
        }
    }
}