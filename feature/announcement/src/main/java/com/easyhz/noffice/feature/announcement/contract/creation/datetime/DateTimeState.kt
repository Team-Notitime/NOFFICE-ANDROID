package com.easyhz.noffice.feature.announcement.contract.creation.datetime

import com.easyhz.noffice.core.common.base.UiState
import java.time.LocalDate
import java.time.LocalTime

data class DateTimeState(
    val selectionDate: LocalDate?,
    val selectionTime: LocalTime?,
    val hour: Int,
    val minute: Int,
    val isAm: Boolean,
): UiState() {
    companion object {
        fun init(): DateTimeState = DateTimeState(
            selectionDate = null,
            selectionTime = null,
            hour = 12,
            minute = 0,
            isAm = true
        )
    }
}
data class SelectionDateTimeState(
    val date: LocalDate? = null,
    val time: LocalTime? = null
)