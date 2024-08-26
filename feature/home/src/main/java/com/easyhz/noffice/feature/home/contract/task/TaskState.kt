package com.easyhz.noffice.feature.home.contract.task

import com.easyhz.noffice.core.common.base.UiState

data class TaskState(
    val isRefreshing: Boolean
): UiState() {
    companion object {
        fun init() = TaskState(
            isRefreshing = false
        )
    }
}
