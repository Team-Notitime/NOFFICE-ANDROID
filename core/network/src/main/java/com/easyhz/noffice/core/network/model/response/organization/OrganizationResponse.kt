package com.easyhz.noffice.core.network.model.response.organization

data class OrganizationResponse(
    val id: Int,
    val name: String,
    val endAt: String?,
    val profileImage: String?,
    val promotion: Boolean,
    val role: String
)