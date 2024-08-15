package com.easyhz.noffice.navigation.organization.screen

import kotlinx.serialization.Serializable

@Serializable
data class OrganizationInvitation(
    val invitationUrl: String,
    val imageUrl: String = "",
)
