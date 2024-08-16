package com.easyhz.noffice.core.model.organization

import com.easyhz.noffice.core.model.organization.category.Category
import com.easyhz.noffice.core.model.organization.member.MemberType
import kotlinx.serialization.Serializable

@Serializable
data class OrganizationInformation(
    val id: Int,
    val name: String,
    val profileImageUrl: String,
    val category: List<Category>,
    val members: LinkedHashMap<MemberType, Int>,
    val hasStandbyMember: Boolean
)
