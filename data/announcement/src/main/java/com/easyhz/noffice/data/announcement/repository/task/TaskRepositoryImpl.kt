package com.easyhz.noffice.data.announcement.repository.task

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.easyhz.noffice.core.common.di.Dispatcher
import com.easyhz.noffice.core.common.di.NofficeDispatchers
import com.easyhz.noffice.core.model.task.AssignedTask
import com.easyhz.noffice.core.model.task.param.TaskParam
import com.easyhz.noffice.core.network.api.task.TaskService
import com.easyhz.noffice.core.network.util.toResult
import com.easyhz.noffice.data.announcement.mapper.task.toModel
import com.easyhz.noffice.data.announcement.mapper.task.toRequest
import com.easyhz.noffice.data.announcement.pagingsource.AssignedTaskPagingSource
import com.easyhz.noffice.data.announcement.pagingsource.AssignedTaskPagingSource.Companion.PAGE_SIZE
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    @Dispatcher(NofficeDispatchers.IO) private val dispatcher: CoroutineDispatcher,
    private val taskService: TaskService,
): TaskRepository {
    override suspend fun fetchAssignedTasks(): Flow<PagingData<AssignedTask>> =
        withContext(dispatcher) {
            return@withContext Pager(
                config = PagingConfig(pageSize = PAGE_SIZE, initialLoadSize = PAGE_SIZE),
                pagingSourceFactory = {
                    AssignedTaskPagingSource(
                        taskService = taskService
                    )
                }
            ).flow.map { pagingData ->
                pagingData.map { response ->
                    response.toModel()
                }
            }
        }

    override suspend fun updateTaskStatus(taskParam: TaskParam): Result<Unit> = withContext(dispatcher) {
        return@withContext taskService.updateTaskStatus(taskParam.toRequest()).toResult()
    }
}