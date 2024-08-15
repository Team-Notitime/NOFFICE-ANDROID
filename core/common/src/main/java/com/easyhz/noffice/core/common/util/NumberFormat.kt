package com.easyhz.noffice.core.common.util

import java.text.NumberFormat
import java.util.Locale

object NumberFormat {
    fun displayNumber(number: Int): String = NumberFormat.getNumberInstance(Locale.US).format(number)
}