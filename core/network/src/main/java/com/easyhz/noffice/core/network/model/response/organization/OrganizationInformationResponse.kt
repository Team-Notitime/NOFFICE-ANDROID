package com.easyhz.noffice.core.network.model.response.organization

data class OrganizationInformationResponse(
    val organizationId: Int,
    val organizationName: String,
    val leaderCount: Int,
    val participantCount: Int,
    val profileImage: String,
    val categories: List<Int>,
    val isPending: Boolean,
)