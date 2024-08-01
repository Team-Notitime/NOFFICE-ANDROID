package com.easyhz.noffice.feature.announcement.contract.creation.task

import com.easyhz.noffice.core.common.base.UiState
import com.easyhz.noffice.core.common.util.Generate

data class TaskState(
    val taskList: MutableList<Task>,
    val isVisibleTextField: Boolean,
    val taskText: String,
    val openBottomSheet: Int
): UiState() {
    companion object {
        fun init(): TaskState = TaskState(
            taskList = mutableListOf(),
            isVisibleTextField = false,
            taskText = "",
            openBottomSheet = -1
        )

        fun TaskState.deleteTaskList(deleteIndex: Int) = copy(
            taskList = this.taskList.filterIndexed { index, _ -> index != deleteIndex }.toMutableList()
        )
    }
}

data class Task(
    val id: String,
    val text: String,
)

internal fun List<String>.toTaskList() = this.map { s -> Task(Generate.randomUUID(), s) }.toMutableList()