package com.easyhz.noffice.core.model.organization.param

import android.net.Uri
import java.time.LocalDate

data class OrganizationCreationParam(
    val name: String,
    val categoryList: List<Int>,
    val profileImage: Uri?,
    val endAt: LocalDate?,
    val promotionCode: String?
)