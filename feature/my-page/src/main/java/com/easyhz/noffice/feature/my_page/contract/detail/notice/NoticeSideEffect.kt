package com.easyhz.noffice.feature.my_page.contract.detail.notice

import com.easyhz.noffice.core.common.base.UiSideEffect
import com.easyhz.noffice.core.model.notice.Notice

sealed class NoticeSideEffect: UiSideEffect() {
    data object NavigateToUp: NoticeSideEffect()
    data class NavigateToNoticeDetail(val item: Notice): NoticeSideEffect()
}