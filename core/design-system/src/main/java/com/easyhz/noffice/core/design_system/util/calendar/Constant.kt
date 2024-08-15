package com.easyhz.noffice.core.design_system.util.calendar

internal const val CONTENT_SIZE = 40
internal const val NUM_OF_DAYS_IN_WEEK = 7
internal const val PADDING_NUM_OF_DAYS_IN_WEEK = NUM_OF_DAYS_IN_WEEK * 2
internal const val WITHOUT_PADDING_NUM = PADDING_NUM_OF_DAYS_IN_WEEK - 2
fun getCalendarPadding(target: Int, screenWidth: Int) =
    (((PADDING_NUM_OF_DAYS_IN_WEEK * target) + (NUM_OF_DAYS_IN_WEEK * CONTENT_SIZE) - screenWidth) / WITHOUT_PADDING_NUM).coerceAtLeast(0)