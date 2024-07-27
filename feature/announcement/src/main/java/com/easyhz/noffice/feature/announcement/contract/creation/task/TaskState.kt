package com.easyhz.noffice.feature.announcement.contract.creation.task

import com.easyhz.noffice.core.common.base.UiState

data class TaskState(
    val taskList: MutableList<String>,
    val isVisibleTextField: Boolean,
    val taskText: String,
): UiState() {
    companion object {
        fun init(): TaskState = TaskState(
            taskList = mutableListOf(),
            isVisibleTextField = false,
            taskText = ""
        )
    }
}