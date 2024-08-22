package com.easyhz.noffice.core.network.api.task

import com.easyhz.noffice.core.network.model.response.task.AssignedTaskResponse
import com.easyhz.noffice.core.network.util.PagingResult
import retrofit2.http.GET
import retrofit2.http.Query

interface TaskService {

    @GET("/api/v1/tasks/assigned")
    suspend fun fetchAssignedTasks(
        @Query("page") page: Int,
        @Query("size") size: Int = 10,
        @Query("sort") sort: List<String>
    ): PagingResult<AssignedTaskResponse>
}