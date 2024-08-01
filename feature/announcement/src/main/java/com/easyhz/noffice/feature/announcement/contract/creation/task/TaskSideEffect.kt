package com.easyhz.noffice.feature.announcement.contract.creation.task

import com.easyhz.noffice.core.common.base.UiSideEffect
import com.easyhz.noffice.feature.announcement.util.creation.OptionData

sealed class TaskSideEffect: UiSideEffect() {
    data object NavigateToUp: TaskSideEffect()
    data class NavigateToNext(val data: OptionData<List<String>>): TaskSideEffect()
    data object ClearFocus: TaskSideEffect()
    data object RequestFocus: TaskSideEffect()
    data class ScrollToBottom(val index: Int) : TaskSideEffect()
}