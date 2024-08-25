package com.easyhz.noffice.data.announcement.mapper.task

import com.easyhz.noffice.core.model.task.AssignedTask
import com.easyhz.noffice.core.model.task.Task
import com.easyhz.noffice.core.model.task.param.TaskParam
import com.easyhz.noffice.core.model.task.param.TaskStatusParam
import com.easyhz.noffice.core.network.model.request.task.TaskRequest
import com.easyhz.noffice.core.network.model.request.task.TaskStatusRequest
import com.easyhz.noffice.core.network.model.response.task.AssignedTaskResponse
import com.easyhz.noffice.core.network.model.response.task.TaskResponse

internal fun TaskResponse.toModel(): Task = Task(
    id = this.id,
    announcementId = this.announcementId,
    content = this.content,
    isDone = this.isMemberChecked ?: false
)

internal fun AssignedTaskResponse.toModel(): AssignedTask = AssignedTask(
    organizationId = this.organizationId,
    organizationName = this.organizationName,
    tasks = this.tasks.map { it.toModel() }
)

internal fun TaskParam.toRequest() = TaskRequest(
    tasks = this.tasks.map { it.toRequest() }
)

internal fun TaskStatusParam.toRequest() = TaskStatusRequest(
    id = this.id,
    status = this.status
)