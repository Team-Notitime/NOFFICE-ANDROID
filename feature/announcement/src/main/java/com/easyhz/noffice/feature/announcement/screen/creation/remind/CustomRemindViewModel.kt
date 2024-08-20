package com.easyhz.noffice.feature.announcement.screen.creation.remind

import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.core.common.util.DateFormat.localDateTimeToRequest
import com.easyhz.noffice.core.common.util.TimeFormat
import com.easyhz.noffice.feature.announcement.contract.creation.remind.custom.CustomRemindIntent
import com.easyhz.noffice.feature.announcement.contract.creation.remind.custom.CustomRemindSideEffect
import com.easyhz.noffice.feature.announcement.contract.creation.remind.custom.CustomRemindState
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class CustomRemindViewModel @Inject constructor(

): BaseViewModel<CustomRemindState, CustomRemindIntent, CustomRemindSideEffect>(
    initialState = CustomRemindState.init()
) {
    override fun handleIntent(intent: CustomRemindIntent) {
        when(intent) {
            is CustomRemindIntent.ClickBackButton -> { onClickBackButton() }
            is CustomRemindIntent.ClickSaveButton -> { onClickSaveButton() }
            is CustomRemindIntent.SelectDate -> { onSelectedDate(intent.date) }
            is CustomRemindIntent.ChangeTimeValue -> { onChangeTimeValue(intent.hour, intent.minute, intent.isAm) }
        }
    }

    private fun onClickBackButton() {
        postSideEffect { CustomRemindSideEffect.NavigateToUp }
    }

    private fun onClickSaveButton() {
        reduce { copy(selectionTime = TimeFormat.convertToLocalTime(hour, minute, isAm)) }
        val dateTime = LocalDateTime.of(currentState.selectionDate, currentState.selectionTime)
        val data = localDateTimeToRequest(dateTime)
        postSideEffect { CustomRemindSideEffect.SaveToDateTime(data) }
    }

    private fun onSelectedDate(date: LocalDate) {
        reduce { copy(selectionDate = date.takeIf { it != currentState.selectionDate }) }
    }

    private fun onChangeTimeValue(hour: Int, minute: Int, isAm: Boolean) {
        reduce { copy(hour = hour, minute = minute, isAm = isAm) }
    }
}