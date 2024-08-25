package com.easyhz.noffice.feature.home.component.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.core.model.task.AssignedTask
import com.easyhz.noffice.domain.home.usecase.task.FetchAssignedTasksUseCase
import com.easyhz.noffice.feature.home.contract.task.TaskIntent
import com.easyhz.noffice.feature.home.contract.task.TaskSideEffect
import com.easyhz.noffice.feature.home.contract.task.TaskState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val fetchAssignedTasksUseCase: FetchAssignedTasksUseCase
): BaseViewModel<TaskState, TaskIntent, TaskSideEffect>(
    initialState = TaskState.init()
) {
    private val _taskListState: MutableStateFlow<PagingData<AssignedTask>> =
        MutableStateFlow(value = PagingData.empty())
    val taskListState: MutableStateFlow<PagingData<AssignedTask>>
        get() = _taskListState
    override fun handleIntent(intent: TaskIntent) {
        when(intent) {
            is TaskIntent.Refresh -> { refresh() }
        }
    }

    init {
        fetchAssignedTasks()
    }

    private fun fetchAssignedTasks() = viewModelScope.launch {
        fetchAssignedTasksUseCase.invoke()
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collectLatest { pagingData ->
                _taskListState.value = pagingData
            }
    }

    private fun refresh() {
        reduce { copy(isRefreshing = true) }
        postSideEffect { TaskSideEffect.Refresh }
        reduce { copy(isRefreshing = false) }
    }
}