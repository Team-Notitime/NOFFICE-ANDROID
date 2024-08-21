package com.easyhz.noffice.core.model.organization.param

import com.easyhz.noffice.core.model.organization.member.MemberType

data class RegisterMemberParam(
    val organizationId: Int,
    val role: MemberType,
    val memberIds: List<Int>
)
