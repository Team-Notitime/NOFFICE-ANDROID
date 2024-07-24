package com.easyhz.noffice.feature.announcement.contract.creation

import com.easyhz.noffice.core.common.base.UiSideEffect

sealed class CreationSideEffect: UiSideEffect() {
    data object NavigateToUp: CreationSideEffect()
    data object NavigateToNext: CreationSideEffect()
    data class NavigateToDateTime(val date: String?, val time: String?): CreationSideEffect()
    data class NavigateToPlace(val contactType: String?, val title: String?, val url: String?): CreationSideEffect()
    data class ScrollToBottom(val cursor: Int): CreationSideEffect()
}