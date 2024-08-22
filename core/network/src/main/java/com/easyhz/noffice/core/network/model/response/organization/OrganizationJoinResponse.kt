package com.easyhz.noffice.core.network.model.response.organization

data class OrganizationJoinResponse(
    val organizationId: Int,
    val organizationName: String,
    val memberId: Int
)
