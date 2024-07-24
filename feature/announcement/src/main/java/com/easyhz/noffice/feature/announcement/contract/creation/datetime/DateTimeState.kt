package com.easyhz.noffice.feature.announcement.contract.creation.datetime

import com.easyhz.noffice.core.common.base.UiState
import java.time.LocalDate
import java.time.LocalTime

data class DateTimeState(
    val selectionDate: LocalDate,
    val selectionTime: LocalTime,
): UiState() {
    companion object {
        fun init(): DateTimeState = DateTimeState(
            selectionDate = LocalDate.now(),
            selectionTime = LocalTime.now()
        )
    }
}
data class SelectionDateTimeState(
    val date: LocalDate = LocalDate.now(),
    val time: LocalTime = LocalTime.now()
)