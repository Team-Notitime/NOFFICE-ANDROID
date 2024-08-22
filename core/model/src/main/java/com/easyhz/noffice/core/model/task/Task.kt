package com.easyhz.noffice.core.model.task

import kotlinx.serialization.Serializable

@Serializable
data class Task(
    val id: Int,
    val announcementId: Int,
    val content: String,
    val isDone: Boolean
)
