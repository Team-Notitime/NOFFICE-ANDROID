package com.easyhz.noffice.data.announcement.mapper.task

import com.easyhz.noffice.core.model.task.Task
import com.easyhz.noffice.core.network.model.response.task.TaskResponse

fun TaskResponse.toModel(): Task = Task(
    id = this.id,
    content = this.content,
    isDone = false
)