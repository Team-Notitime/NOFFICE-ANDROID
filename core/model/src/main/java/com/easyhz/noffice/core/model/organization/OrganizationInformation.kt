package com.easyhz.noffice.core.model.organization

import com.easyhz.noffice.core.model.organization.member.MemberType
import kotlinx.serialization.Serializable

@Serializable
data class OrganizationInformation(
    val id: Int,
    val name: String,
    val profileImageUrl: String,
    val category: List<String>,
    val members: LinkedHashMap<MemberType, Int>,
    val hasStandbyMember: Boolean,
    val role: MemberType
)
