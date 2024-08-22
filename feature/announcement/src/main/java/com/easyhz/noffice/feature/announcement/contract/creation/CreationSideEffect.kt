package com.easyhz.noffice.feature.announcement.contract.creation

import com.easyhz.noffice.core.common.base.UiSideEffect
import com.easyhz.noffice.core.model.announcement.param.AnnouncementParam

sealed class CreationSideEffect: UiSideEffect() {
    data object NavigateToUp: CreationSideEffect()
    data class NavigateToNext(val param: AnnouncementParam): CreationSideEffect()
    data class NavigateToDateTime(val date: String?, val time: String?): CreationSideEffect()
    data class NavigateToPlace(val contactType: String?, val title: String?, val url: String?): CreationSideEffect()
    data class NavigateToTask(val taskList: List<String>?): CreationSideEffect()
    data class NavigateToRemind(val remindList: List<String>?, val isSelectedDateTime: Boolean): CreationSideEffect()
    data class ScrollToBottom(val cursor: Int): CreationSideEffect()
}