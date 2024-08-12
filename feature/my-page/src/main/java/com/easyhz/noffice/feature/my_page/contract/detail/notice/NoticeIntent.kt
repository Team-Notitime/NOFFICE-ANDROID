package com.easyhz.noffice.feature.my_page.contract.detail.notice

import com.easyhz.noffice.core.common.base.UiIntent

sealed class NoticeIntent: UiIntent() {
    data object ClickBackButton: NoticeIntent()
    data class ClickNotice(val index: Int): NoticeIntent()
}