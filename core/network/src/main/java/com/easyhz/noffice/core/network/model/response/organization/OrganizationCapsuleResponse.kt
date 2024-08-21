package com.easyhz.noffice.core.network.model.response.organization

data class OrganizationCapsuleResponse(
    val organizationId: Int,
    val organizationName: String,
    val profileImage: String?,
    val role: String,
    val joinStatus: String,
)