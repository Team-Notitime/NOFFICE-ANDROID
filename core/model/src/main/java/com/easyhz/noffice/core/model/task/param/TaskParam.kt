package com.easyhz.noffice.core.model.task.param

import com.easyhz.noffice.core.model.task.Task

data class TaskParam(
    val tasks: List<TaskStatusParam>
)

data class TaskStatusParam(
    val id: Int,
    val status: Boolean
)

fun Task.toTaskParam(): TaskParam {
    return TaskParam(
        tasks = listOf(TaskStatusParam(id = id, status = !this.isDone))
    )
}