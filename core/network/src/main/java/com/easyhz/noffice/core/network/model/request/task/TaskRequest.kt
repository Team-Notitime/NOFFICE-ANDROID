package com.easyhz.noffice.core.network.model.request.task

data class TaskRequest(
    val tasks: List<TaskStatusRequest>
)

data class TaskStatusRequest(
    val id: Int,
    val status: Boolean
)