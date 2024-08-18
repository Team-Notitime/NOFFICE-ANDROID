package com.easyhz.noffice.core.network.model.response.auth

import com.easyhz.noffice.core.network.model.response.organization.OrganizationCapsuleResponse

data class UserInfoResponse(
    val alias: String,
    val id: Int,
    val name: String,
    val organizations: List<OrganizationCapsuleResponse>,
    val profileImage: String
)