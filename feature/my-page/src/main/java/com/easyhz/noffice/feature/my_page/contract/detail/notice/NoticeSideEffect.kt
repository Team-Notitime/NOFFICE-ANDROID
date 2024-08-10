package com.easyhz.noffice.feature.my_page.contract.detail.notice

import com.easyhz.noffice.core.common.base.UiSideEffect

sealed class NoticeSideEffect: UiSideEffect() {
    data object NavigateToUp: NoticeSideEffect()
}