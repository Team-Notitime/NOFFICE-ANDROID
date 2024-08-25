package com.easyhz.noffice.domain.announcement.usecase.task

import com.easyhz.noffice.core.common.base.BaseUseCase
import com.easyhz.noffice.core.model.task.param.TaskParam
import com.easyhz.noffice.data.announcement.repository.task.TaskRepository
import javax.inject.Inject

class UpdateTaskStatusUseCase @Inject constructor(
    private val taskRepository: TaskRepository
): BaseUseCase<TaskParam, Unit>() {
    override suspend fun invoke(param: TaskParam): Result<Unit> {
        return taskRepository.updateTaskStatus(param)
    }
}