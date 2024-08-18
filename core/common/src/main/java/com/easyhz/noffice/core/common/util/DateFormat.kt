package com.easyhz.noffice.core.common.util

import java.time.LocalDate
import java.time.LocalTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.time.format.TextStyle
import java.util.Locale

object DateFormat {
    enum class PATTERN(
        val value: String
    ) {
        FULL("yyyy.MM.dd(E) HH:mm"),
        DATE_TEXT("yyyy년 MM월 dd일"),
        DATE_DASH("yyyy-MM-dd"),
        DAY("MM/dd"),
        TIME("HH:mm")
    }
    fun fullText(date: LocalDate): String =
        DateTimeFormatter.ofPattern(PATTERN.DATE_TEXT.value).format(date)

    fun stringToLocalDate(date: String, pattern: PATTERN = PATTERN.DATE_DASH): LocalDate = try {
        val formatter = DateTimeFormatter.ofPattern(pattern.value)
        LocalDate.parse(date, formatter)
    } catch (e: DateTimeParseException) {
        e.printStackTrace()
        LocalDate.now()
    }


    fun stringToLocalTime(time: String, pattern: PATTERN = PATTERN.TIME): LocalTime = try {
        val formatter = DateTimeFormatter.ofPattern(pattern.value)
        LocalTime.parse(time, formatter)
    } catch (e: DateTimeParseException) {
        e.printStackTrace()
        LocalTime.now()
    }

    fun formatDateTime(date: String, pattern: PATTERN = PATTERN.FULL): String {
        val dateTime = ZonedDateTime.parse(date)

        val formatter = DateTimeFormatter.ofPattern(pattern.value)
            .withLocale(Locale.KOREAN)
        val formattedDate = dateTime.format(formatter)

        return formattedDate
    }

    fun formatDateTimeNullable(date: String?, pattern: PATTERN = PATTERN.FULL): String? {
        if (date.isNullOrBlank()) return null
        val dateTime = ZonedDateTime.parse(date)

        val formatter = DateTimeFormatter.ofPattern(pattern.value)
            .withLocale(Locale.KOREAN)
        val formattedDate = dateTime.format(formatter)

        return formattedDate
    }

    fun localDateToRequest(date: LocalDate?): String? {
        if (date == null) return null
        val dateTime = date.atStartOfDay()
        val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
        return dateTime.format(formatter)
    }

    fun getDayOfWeek(): String {
        val currentDate = LocalDate.now()
        val dayOfWeek = currentDate.dayOfWeek
        return dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
    }
}