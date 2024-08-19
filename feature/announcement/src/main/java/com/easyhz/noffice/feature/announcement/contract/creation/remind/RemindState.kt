package com.easyhz.noffice.feature.announcement.contract.creation.remind

import com.easyhz.noffice.core.common.base.UiState

data class RemindState(
    val remindMap: LinkedHashMap<String, Boolean>,
    val originalMap: LinkedHashMap<String, Boolean>
) : UiState() {
    companion object {
        fun init(): RemindState = RemindState(
            remindMap = timeList.toRemindMap(),
            originalMap = timeList.toRemindMap()
        )
        fun RemindState.initRemindMap(remindList: List<String>): RemindState =
            copy(remindMap = LinkedHashMap(remindMap).apply {
                remindList.forEach { key ->
                    this[key] = true
                }
            })

        fun RemindState.toggleMap(key: String): RemindState =
            copy(remindMap = LinkedHashMap(remindMap).apply {
                this[key] = this[key]?.not() ?: false
            })
    }
}

internal val timeList = listOf(
    (0).toString(),
    (5 * 60).toString(),
    (10 * 60).toString(),
    (15 * 60).toString(),
    (30 * 60).toString(),
    (1 * 60 * 60).toString(),
    (2 * 60 * 60).toString(),
    (3 * 60 * 60).toString(),
    (12 * 60 * 60).toString(),
    (24 * 60 * 60).toString(),
    (48 * 60 * 60).toString(),
    (168 * 60 * 60).toString()
)

internal fun secondsToString(s: String): String {
    val seconds = s.toIntOrNull() ?: return s

    val weeks = seconds / (60 * 60 * 24 * 7)
    val days = (seconds % (60 * 60 * 24 * 7)) / (60 * 60 * 24)
    val hours = (seconds % (60 * 60 * 24)) / (60 * 60)
    val minutes = (seconds % (60 * 60)) / 60
    val secondsLeft = seconds % 60

    return when {
        seconds == 0 -> "정시"
        weeks > 0 -> "${weeks}주(${weeks * 7 * 24}시간) 전"
        days > 0 -> "${days}일(${days * 24}시간) 전"
        hours > 0 -> "${hours}시간 전"
        minutes > 0 -> "${minutes}분 전"
        else -> "${secondsLeft}초 전"
    }
}

internal fun List<String>.toRemindMap() = LinkedHashMap(this.associateWith { false })
