package com.easyhz.noffice.core.model.organization.param

import java.time.LocalDate

data class OrganizationCreationParam(
    val name: String,
    val categoryList: List<Int>,
    val profileImage: String?,
    val endAt: LocalDate?,
    val promotionCode: String?
)