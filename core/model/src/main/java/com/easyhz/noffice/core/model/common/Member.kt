package com.easyhz.noffice.core.model.common

import com.easyhz.noffice.core.model.organization.member.MemberType

data class Member(
    val alias: String,
    val id: Int,
    val name: String,
    val profileImage: String?,
    val role: MemberType,
    val isSelected: Boolean,
)
