package com.easyhz.noffice.core.network.model.request.organization

data class OrganizationCreationRequest(
    val name: String,
    val categoryList: List<Int>,
    val profileImage: String?,
    val endAt: String?,
    val promotionCode: String?
)