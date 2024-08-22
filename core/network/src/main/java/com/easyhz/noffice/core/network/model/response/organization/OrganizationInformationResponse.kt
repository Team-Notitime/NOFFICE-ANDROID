package com.easyhz.noffice.core.network.model.response.organization

data class OrganizationInformationResponse(
    val organizationId: Int,
    val organizationName: String,
    val leaderCount: Int,
    val participantCount: Int,
    val profileImage: String,
    val categories: List<String>,
    val isPending: Boolean,
    val role: String
)