 package com.easyhz.noffice.core.common.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DateFormat {
    fun fullText(date: LocalDate): String =
        DateTimeFormatter.ofPattern("yyyy년 MM월 d일").format(date)
}