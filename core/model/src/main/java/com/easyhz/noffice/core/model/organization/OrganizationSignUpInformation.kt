package com.easyhz.noffice.core.model.organization

import kotlinx.serialization.Serializable

@Serializable
data class OrganizationSignUpInformation(
    val organizationId: Int,
    val organizationName: String,
    val profileImage: String,
)
