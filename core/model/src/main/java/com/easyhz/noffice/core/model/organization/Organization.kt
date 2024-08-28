package com.easyhz.noffice.core.model.organization

import com.easyhz.noffice.core.model.organization.member.MemberType

data class Organization(
    val id: Int,
    val name: String,
    val profileImageUrl: String?,
    val role: MemberType,
    val joinStatus: JoinStatus?,
)

enum class JoinStatus {
    ACTIVE, PENDING, REJECTED, DELETED
}
