package com.easyhz.noffice.core.network.model.response.task

data class AssignedTaskResponse(
    val organizationId: Int,
    val organizationName: String,
    val tasks: List<TaskResponse>
)
