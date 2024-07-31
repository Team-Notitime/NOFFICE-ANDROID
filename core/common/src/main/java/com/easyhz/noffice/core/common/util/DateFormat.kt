package com.easyhz.noffice.core.common.util

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

object DateFormat {
    fun fullText(date: LocalDate): String =
        DateTimeFormatter.ofPattern("yyyy년 MM월 d일").format(date)

    fun stringToLocalDate(date: String, pattern: String = "yyyy-MM-dd"): LocalDate = try {
        val formatter = DateTimeFormatter.ofPattern(pattern)
        LocalDate.parse(date, formatter)
    } catch (e: DateTimeParseException) {
        e.printStackTrace()
        LocalDate.now()
    }


    fun stringToLocalTime(time: String, pattern: String = "HH:mm"): LocalTime = try {
        val formatter = DateTimeFormatter.ofPattern(pattern)
        LocalTime.parse(time, formatter)
    } catch (e: DateTimeParseException) {
        e.printStackTrace()
        LocalTime.now()
    }


}