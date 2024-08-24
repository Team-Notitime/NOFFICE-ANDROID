package com.easyhz.noffice.navigation.organization.screen

import kotlinx.serialization.Serializable

@Serializable
data class MemberManagement(
    val organizationId: Int,
    val imageUrl: String?,
)
