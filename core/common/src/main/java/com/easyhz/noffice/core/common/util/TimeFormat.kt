package com.easyhz.noffice.core.common.util

import java.time.LocalTime

object TimeFormat {
    fun getHourMinuteIsAm(localTime: LocalTime): Triple<Int, Int, Boolean> {
        val hour = if(localTime.hour % 12 == 0) 12 else localTime.hour % 12
        val minute = localTime.minute
        val isAm = localTime.hour < 12
        return Triple(hour, minute, isAm)
    }

    fun convertToLocalTime(hour: Int, minute: Int, isAM: Boolean): LocalTime {
        val adjustedHour = if (isAM) {
            if (hour == 12) 0 else hour
        } else {
            if (hour == 12) 12 else hour + 12
        }
        return LocalTime.of(adjustedHour, minute, 0, 0)
    }
}