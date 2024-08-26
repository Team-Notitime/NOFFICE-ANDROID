package com.easyhz.noffice.feature.home.contract.task

import com.easyhz.noffice.core.common.base.UiIntent
import com.easyhz.noffice.core.model.task.Task

sealed class TaskIntent: UiIntent() {
    data object Refresh: TaskIntent()
    data class UpdateTaskStatus(val task: Task): TaskIntent()
}