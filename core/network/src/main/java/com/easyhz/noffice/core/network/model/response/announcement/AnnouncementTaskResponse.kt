package com.easyhz.noffice.core.network.model.response.announcement

import com.easyhz.noffice.core.network.model.response.task.TaskResponse

data class AnnouncementTaskResponse(
    val tasks: List<TaskResponse>
)
