package com.easyhz.noffice.core.model.announcement.detail

import com.easyhz.noffice.core.model.common.Member

data class AnnouncementReader(
    val announcementId: Int,
    val memberList: List<Member>
)
