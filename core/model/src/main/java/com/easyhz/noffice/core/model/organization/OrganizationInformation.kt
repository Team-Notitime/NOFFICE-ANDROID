package com.easyhz.noffice.core.model.organization

import kotlinx.serialization.Serializable

@Serializable
data class OrganizationInformation(
    val id: Int,
    val name: String,
    val profileImageUrl: String,
    val category: List<String>,
)
