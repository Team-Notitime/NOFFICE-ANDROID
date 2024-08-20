package com.easyhz.noffice.feature.announcement.util.creation

import com.easyhz.noffice.core.common.util.DateFormat.localDateTimeToRequest
import com.easyhz.noffice.core.common.util.DateFormat.parseLocalDateTime
import com.easyhz.noffice.feature.announcement.contract.creation.remind.timeList

internal fun separateRemind(remindList: List<String>): Pair<List<String>, List<String>> {
    return remindList.partition { it in timeList }
}

internal fun calculateRemind(remindList: List<String>, endAt: String?): Triple<List<Long>, List<String>, List<String>> {
    val (time, custom) = separateRemind(remindList)
    val secondList = mutableListOf<Long>()
    val timeList = mutableListOf<String>()

    endAt?.let { endAtStr ->
        val endDateTime = parseLocalDateTime(endAtStr) ?: return Triple(secondList, timeList, custom)
        time.forEach { timeString ->
            timeString.toLongOrNull()?.let { sec ->
                secondList.add(sec)
                timeList.add(localDateTimeToRequest(endDateTime.minusSeconds(sec)))
            }
        }
    }

    return Triple(secondList, timeList, custom)
}