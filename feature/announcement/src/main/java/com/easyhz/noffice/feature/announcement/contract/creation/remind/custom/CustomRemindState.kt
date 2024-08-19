package com.easyhz.noffice.feature.announcement.contract.creation.remind.custom

import com.easyhz.noffice.core.common.base.UiState
import com.easyhz.noffice.core.common.util.TimeFormat
import java.time.LocalDate
import java.time.LocalTime

data class CustomRemindState(
    val selectionDate: LocalDate?,
    val selectionTime: LocalTime?,
    val hour: Int,
    val minute: Int,
    val isAm: Boolean,
): UiState() {
    companion object {
        fun init(): CustomRemindState {
            val currentTime = LocalTime.now()
            val (hour, minute, isAm) = TimeFormat.getHourMinuteIsAm(currentTime)

            return CustomRemindState(
                selectionDate = LocalDate.now(),
                selectionTime = currentTime,
                hour = hour,
                minute = minute,
                isAm = isAm
            )
        }
    }
}
