package com.easyhz.noffice.data.announcement.repository.task

import androidx.paging.PagingData
import com.easyhz.noffice.core.model.task.AssignedTask
import com.easyhz.noffice.core.model.task.param.TaskParam
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun fetchAssignedTasks(): Flow<PagingData<AssignedTask>>
    suspend fun updateTaskStatus(taskParam: TaskParam): Result<Unit>
}