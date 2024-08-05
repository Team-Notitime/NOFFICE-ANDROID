package com.easyhz.noffice.navigation.organization.screen

import kotlinx.serialization.Serializable

@Serializable
data class OrganizationDetail(
    val organizationId: Int,
    val organizationName: String
)
