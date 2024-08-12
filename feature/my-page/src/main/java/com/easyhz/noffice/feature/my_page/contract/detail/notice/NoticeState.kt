package com.easyhz.noffice.feature.my_page.contract.detail.notice

import com.easyhz.noffice.core.common.base.UiState
import com.easyhz.noffice.core.model.notice.Notice

data class NoticeState(
    val noticeList: List<Notice>
): UiState() {
    companion object {
        fun init() = NoticeState(
            noticeList = DUMMY
        )
    }
}

// FIXME
val DUMMY = listOf(
    Notice(
        title = "업데이트 알림",
        content = "업데이트 알림 업데이트 알림 업데이트 알림 업데이트  알림업데이트 알림업데이트 알림업데이트 알림 업데이트 알림 업데이트알림 업데이트알림 업데이트알림 업데이트 알림업데이트 알림업데이트 알림",
        date = "2024.07.07"
    ),Notice(
        title = "업데이트 알림",
        content = "업데이트 알림업데이트 알림업데이트 알림업데이트 알림업데이트 알림업데이트 알림업데이트 알림업데이트 알림 알림 업데이트알림 업데이트알림 업데이트",
        date = "2024.07.07"
    ),Notice(
        title = "업데이트 알림",
        content = "업데이트 알림업데이트 알림업데이트 알림업데이트 알림업데이트 알림업데이트 ",
        date = "2024.07.07"
    )
)