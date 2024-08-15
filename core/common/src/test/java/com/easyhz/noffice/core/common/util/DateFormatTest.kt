package com.easyhz.noffice.core.common.util

import org.junit.Test
import org.junit.Assert.*
import java.time.LocalDate

class DateFormatTest {
    @Test
    fun formatDateTimeTest() {
        val input = "2024-08-04T05:13:51.921Z"
        assertEquals(DateFormat.formatDateTime(input), "2024.08.04(일) 05:13")
    }

    @Test
    fun `formatDateTimeTest - custom pattern`() {
        val input = "2024-08-04T05:13:51.921Z"
        assertEquals(DateFormat.formatDateTime(input, pattern = DateFormat.PATTERN.DAY), "08/04")
    }

    @Test
    fun localDateToRequestTest() {
        val input = LocalDate.of(2024, 8, 15)
        assertEquals(DateFormat.localDateToRequest(input), "2024-08-15T00:00:00")
    }
}