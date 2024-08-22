package com.easyhz.noffice.data.announcement.repository.task

import androidx.paging.PagingData
import com.easyhz.noffice.core.model.task.AssignedTask
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun fetchAssignedTasks(): Flow<PagingData<AssignedTask>>
}