package com.easyhz.noffice.feature.announcement.contract.creation.task

import com.easyhz.noffice.core.common.base.UiIntent

sealed class TaskIntent: UiIntent() {
    data class InitScreen(val taskList: List<String>?): TaskIntent()
    data object ClickBackButton: TaskIntent()
    data object ClickSaveButton: TaskIntent()
    data object ClickTaskButton: TaskIntent()
    data class ChangeTaskTextValue(val newText: String): TaskIntent()
    data object ClearFocus: TaskIntent()
    data object SaveTask: TaskIntent()
}
