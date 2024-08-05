package com.easyhz.noffice.core.model.announcement.detail

import com.easyhz.noffice.core.model.task.Task

data class AnnouncementDetail(
    val id: Int,
    val title: String,
    val creationDate: String,
    val organizationName: String,
    val organizationCategory: String,
    val organizationProfileImage: String,
    val date: String,
    val place: String,
    val placeUrl: String,
    val content: String,
    val taskList: List<Task>
)