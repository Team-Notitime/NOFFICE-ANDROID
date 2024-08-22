package com.easyhz.noffice.core.model.task

data class AssignedTask(
    val organizationId: Int,
    val organizationName: String,
    val tasks: List<Task>
)
