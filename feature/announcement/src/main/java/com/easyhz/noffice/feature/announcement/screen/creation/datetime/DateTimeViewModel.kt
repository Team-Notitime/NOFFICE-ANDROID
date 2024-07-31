package com.easyhz.noffice.feature.announcement.screen.creation.datetime

import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.core.common.util.DateFormat
import com.easyhz.noffice.core.common.util.TimeFormat
import com.easyhz.noffice.feature.announcement.contract.creation.datetime.DateTimeIntent
import com.easyhz.noffice.feature.announcement.contract.creation.datetime.DateTimeSideEffect
import com.easyhz.noffice.feature.announcement.contract.creation.datetime.DateTimeState
import com.easyhz.noffice.feature.announcement.contract.creation.datetime.SelectionDateTimeState
import com.easyhz.noffice.feature.announcement.util.creation.OptionData
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class DateTimeViewModel @Inject constructor(

): BaseViewModel<DateTimeState, DateTimeIntent, DateTimeSideEffect>(
    initialState = DateTimeState.init()
) {
    override fun handleIntent(intent: DateTimeIntent) {
        when(intent) {
            is DateTimeIntent.InitScreen -> { initScreen(intent.date, intent.time) }
            is DateTimeIntent.ClickBackButton -> { onClickBackButton() }
            is DateTimeIntent.ClickSaveButton -> { onClickSaveButton() }
            is DateTimeIntent.SelectDate -> { onSelectedDate(intent.date) }
            is DateTimeIntent.ChangeTimeValue -> { onChangeTimeValue(intent.hour, intent.minute, intent.isAm) }
        }
    }

    private fun initScreen(date: String?, time: String?) {
        if (date == null || time == null) return
        reduce { copy(selectionDate = DateFormat.stringToLocalDate(date), selectionTime = DateFormat.stringToLocalTime(time)) }
    }

    private fun onClickBackButton() {
        postSideEffect { DateTimeSideEffect.NavigateToUp }
    }

    private fun onClickSaveButton() {
        reduce { copy(selectionTime = TimeFormat.convertToLocalTime(hour, minute, isAm)) }
        val dateTimeState = SelectionDateTimeState(
            currentState.selectionDate,
            currentState.selectionTime
        )
        val isSelected = dateTimeState.date != null && dateTimeState.time != null
        postSideEffect {
            DateTimeSideEffect.NavigateToNext(OptionData.DateTime(dateTimeState, isSelected))
        }
    }
    private fun onSelectedDate(date: LocalDate) {
        reduce { copy(selectionDate = date.takeIf { it != currentState.selectionDate }) }
    }

    private fun onChangeTimeValue(hour: Int, minute: Int, isAm: Boolean) {
        reduce { copy(hour = hour, minute = minute, isAm = isAm) }
    }
}