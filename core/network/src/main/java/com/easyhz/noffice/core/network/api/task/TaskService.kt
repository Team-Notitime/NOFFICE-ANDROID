package com.easyhz.noffice.core.network.api.task

import com.easyhz.noffice.core.network.model.request.task.TaskRequest
import com.easyhz.noffice.core.network.model.response.task.AssignedTaskResponse
import com.easyhz.noffice.core.network.util.NofficeResult
import com.easyhz.noffice.core.network.util.PagingResult
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query

interface TaskService {

    /* 할당된 할 일 조회 */
    @GET("/api/v1/tasks/assigned")
    suspend fun fetchAssignedTasks(
        @Query("page") page: Int,
        @Query("size") size: Int = 10,
        @Query("sort") sort: List<String>? = null
    ): PagingResult<AssignedTaskResponse>

    @PUT("/api/v1/tasks/assigned")
    suspend fun updateTaskStatus(
        @Body request: TaskRequest
    ): NofficeResult<Unit>
}