package com.easyhz.noffice.domain.home.usecase.task

import androidx.paging.PagingData
import com.easyhz.noffice.core.model.task.AssignedTask
import com.easyhz.noffice.data.announcement.repository.task.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchAssignedTasksUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(): Flow<PagingData<AssignedTask>> {
        return taskRepository.fetchAssignedTasks()
    }
}