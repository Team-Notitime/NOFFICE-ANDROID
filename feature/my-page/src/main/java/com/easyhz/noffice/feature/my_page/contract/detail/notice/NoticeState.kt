package com.easyhz.noffice.feature.my_page.contract.detail.notice

import com.easyhz.noffice.core.common.base.UiState

data class NoticeState(
    val noticeList: List<String>
): UiState()