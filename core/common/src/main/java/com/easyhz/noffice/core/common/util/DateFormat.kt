package com.easyhz.noffice.core.common.util

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.time.format.TextStyle
import java.util.Locale

object DateFormat {
    enum class PATTERN(
        val value: String
    ) {
        FULL("yyyy.MM.dd(E) HH:mm"),
        DATE_TIME_TEXT("yyyy.MM.dd HH:mm"),
        DATE_TEXT("yyyy년 MM월 dd일"),
        DATE_DASH("yyyy-MM-dd"),
        CUSTOM_REMIND("MM월 dd일 E a h:mm"),
        DAY("MM/dd"),
        TIME("HH:mm"),
        REQUEST("yyyy-MM-dd'T'HH:mm:ss")
    }

    fun fullText(date: LocalDate): String =
        DateTimeFormatter.ofPattern(PATTERN.DATE_TEXT.value).format(date)

    /**
     * [String] 을 [LocalDate]로 변환
     *
     * 달력에서 날짜를 선택할 때 주로 쓰입니다
     */
    fun stringToLocalDate(date: String, pattern: PATTERN = PATTERN.DATE_DASH): LocalDate = try {
        val formatter = DateTimeFormatter.ofPattern(pattern.value)
        LocalDate.parse(date, formatter)
    } catch (e: DateTimeParseException) {
        e.printStackTrace()
        LocalDate.now()
    }

    /**
     * [String] 을 [LocalTime]로 변환
     *
     * 달력에서 시간을 선택할 때 주로 쓰입니다
     */
    fun stringToLocalTime(time: String, pattern: PATTERN = PATTERN.TIME): LocalTime = try {
        val formatter = DateTimeFormatter.ofPattern(pattern.value)
        LocalTime.parse(time, formatter)
    } catch (e: DateTimeParseException) {
        e.printStackTrace()
        LocalTime.now()
    }

    fun formatDateTime(date: String, pattern: PATTERN = PATTERN.FULL): String {
        val dateTime = LocalDateTime.parse(date)

        val formatter = DateTimeFormatter.ofPattern(pattern.value)
        val formattedDate = dateTime.format(formatter)

        return formattedDate
    }

    fun formatDateTimeNullable(date: String?, pattern: PATTERN = PATTERN.FULL): String? {
        if (date.isNullOrBlank()) return null
        val dateTime = LocalDateTime.parse(date)

        val formatter = DateTimeFormatter.ofPattern(pattern.value)
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

    fun localDateTimeToString(dateTime: String, pattern: PATTERN = PATTERN.CUSTOM_REMIND): String {
        val parsDate = LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        val outputFormatter = DateTimeFormatter.ofPattern(pattern.value, Locale.KOREAN)
        val formattedDate = parsDate.format(outputFormatter)
        return formattedDate
    }

    fun dateTimeToRequestStringNullable(date: LocalDate?, time: LocalTime?): String? {
        if (date == null || time == null) return null
        val dateTime = LocalDateTime.of(date, time)
        val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
        return dateTime.format(formatter)
    }

    fun localDateTimeToRequest(dateTime: LocalDateTime): String {
        val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
        return dateTime.format(formatter)
    }

    fun parseLocalDateTime(input: String): LocalDateTime? =
        LocalDateTime.parse(input, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
}