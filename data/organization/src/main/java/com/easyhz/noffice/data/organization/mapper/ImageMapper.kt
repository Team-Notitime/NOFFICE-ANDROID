package com.easyhz.noffice.data.organization.mapper

import com.easyhz.noffice.core.model.image.ImageUrl
import com.easyhz.noffice.core.network.model.response.image.ImageUrlResponse

fun ImageUrlResponse.toModel(): ImageUrl = ImageUrl(
    fileName = this.fileName,
    url = this.urls.url
)