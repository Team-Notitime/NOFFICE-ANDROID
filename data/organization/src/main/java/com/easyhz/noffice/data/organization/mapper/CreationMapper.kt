package com.easyhz.noffice.data.organization.mapper

import com.easyhz.noffice.core.common.util.DateFormat
import com.easyhz.noffice.core.model.organization.param.OrganizationCreationParam
import com.easyhz.noffice.core.network.model.request.organization.OrganizationCreationRequest

internal fun OrganizationCreationParam.toRequest(): OrganizationCreationRequest = OrganizationCreationRequest(
    name = this.name,
    categoryList = this.categoryList,
    profileImage = this.profileImage.toString(),
    endAt = DateFormat.localDateToRequest(this.endAt),
    promotionCode = this.promotionCode
)