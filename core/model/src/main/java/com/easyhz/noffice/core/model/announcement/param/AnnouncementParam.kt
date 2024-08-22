package com.easyhz.noffice.core.model.announcement.param

import com.easyhz.noffice.core.model.task.Task
import kotlinx.serialization.Serializable

@Serializable
data class AnnouncementParam(
    val content: String,
    val endAt: String?,
    val isFaceToFace: Boolean?,
    val memberId: Int,
    val noticeBefore: List<String>?,
    val noticeDate: List<String>?,
    val organizationId: Int,
    val placeLinkName: String?,
    val placeLinkUrl: String?,
    val profileImageUrl: String?,
    val tasks: List<Task>?,
    val title: String
)