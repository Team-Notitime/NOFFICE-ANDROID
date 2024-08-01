package com.easyhz.noffice.feature.announcement.screen.creation.task

import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.core.common.util.Generate
import com.easyhz.noffice.feature.announcement.contract.creation.task.Task
import com.easyhz.noffice.feature.announcement.contract.creation.task.TaskIntent
import com.easyhz.noffice.feature.announcement.contract.creation.task.TaskSideEffect
import com.easyhz.noffice.feature.announcement.contract.creation.task.TaskState
import com.easyhz.noffice.feature.announcement.contract.creation.task.TaskState.Companion.deleteTaskList
import com.easyhz.noffice.feature.announcement.contract.creation.task.toTaskList
import com.easyhz.noffice.feature.announcement.util.creation.OptionData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(

): BaseViewModel<TaskState, TaskIntent, TaskSideEffect>(
    initialState = TaskState.init()
) {
    override fun handleIntent(intent: TaskIntent) {
        when(intent) {
            is TaskIntent.InitScreen -> { initScreen(intent.taskList) }
            is TaskIntent.ClickBackButton -> { onClickBackButton() }
            is TaskIntent.ClickSaveButton -> { onClickSaveButton() }
            is TaskIntent.ClickTaskButton -> { onClickTaskButton() }
            is TaskIntent.ChangeTaskTextValue -> { onChangeTaskTextValue(intent.newText) }
            is TaskIntent.ClearFocus -> { onClearFocus() }
            is TaskIntent.SaveTask -> { onSaveTask() }
            is TaskIntent.OpenDeleteBottomSheet -> { onOpenDeleteBottomSheet(intent.index) }
            is TaskIntent.CloseDeleteBottomSheet -> { onCloseDeleteBottomSheet() }
            is TaskIntent.DeleteTask -> { onDeleteTask(intent.index) }
        }
    }

    private fun initScreen(taskList: List<String>?) {
        reduce { copy(taskList = taskList?.toTaskList() ?: mutableListOf()) }
    }

    private fun onClickBackButton() {
        postSideEffect { TaskSideEffect.NavigateToUp }
    }

    private fun onClickSaveButton() {
        val taskList = currentState.taskList.map { it.text }
        postSideEffect {
            TaskSideEffect.NavigateToNext(OptionData.Task(data = taskList, isSelected = taskList.isNotEmpty()))
        }
    }

    private fun onClickTaskButton() {
        reduce { copy(isVisibleTextField = true) }
        postSideEffect { TaskSideEffect.RequestFocus }
    }

    private fun onChangeTaskTextValue(newText: String) {
        reduce { copy(taskText = newText) }
    }

    private fun onClearFocus() {
        if (currentState.isVisibleTextField) {
            reduce { copy(isVisibleTextField = false, taskText = "") }
            clearFocus()
        }
    }

    private fun onSaveTask() {
        if (currentState.taskText.isBlank()) return
        currentState.taskList.add(Task(Generate.randomUUID(), currentState.taskText))
        reduce { copy(taskText = "") }
        postSideEffect { TaskSideEffect.ScrollToBottom(currentState.taskList.lastIndex) }
    }

    private fun onOpenDeleteBottomSheet(index: Int) {
        reduce { copy(openBottomSheet = index) }
    }

    private fun onCloseDeleteBottomSheet() {
        reduce { copy(openBottomSheet = -1) }
        onClearFocus()
    }

    private fun onDeleteTask(index: Int) {
        reduce { deleteTaskList(index) }
        onCloseDeleteBottomSheet()
    }

    private fun clearFocus() {
        postSideEffect { TaskSideEffect.ClearFocus }
    }
}