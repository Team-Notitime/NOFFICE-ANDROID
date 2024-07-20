package com.easyhz.noffice.core.design_system.util.calendar

import java.time.DayOfWeek
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

internal val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("d")

internal fun YearMonth.displayText(): String =
    "${year}.${month.value}"
internal fun DayOfWeek.displayText(uppercase: Boolean = false): String =
    getDisplayName(TextStyle.SHORT, Locale.KOREA).let { value ->
        if (uppercase) value.uppercase(Locale.KOREA) else value
    }