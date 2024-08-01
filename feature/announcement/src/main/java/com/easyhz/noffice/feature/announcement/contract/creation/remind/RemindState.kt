package com.easyhz.noffice.feature.announcement.contract.creation.remind

import com.easyhz.noffice.core.common.base.UiState

data class RemindState(
    val remindMap: LinkedHashMap<String, Boolean>,
) : UiState() {
    companion object {
        fun init(): RemindState = RemindState(
            remindMap = timeList.toRemindMap()
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

// FIXME
private val timeList = listOf(
    "정시",
    "5분 전",
    "10분 전",
    "15분 전",
    "30분 전",
    "1시간 전",
    "2시간 전",
    "3시간 전",
    "12시간 전",
    "1일(24시간) 전",
    "2일(48시간) 전",
    "1주(168시간) 전"
)

private fun List<String>.toRemindMap() = LinkedHashMap(this.associateWith { false })
