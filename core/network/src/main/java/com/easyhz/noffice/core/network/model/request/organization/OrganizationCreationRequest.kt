package com.easyhz.noffice.core.network.model.request.organization

data class OrganizationCreationRequest(
    val categoryList: List<Int>,
    val endAt: String,
    val name: String,
    val profileImage: String,
    val promotionCode: String
)