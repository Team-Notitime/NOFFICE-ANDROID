package com.easyhz.noffice.core.model.organization

import com.easyhz.noffice.core.model.image.ImagePurpose

data class SelectableCover(
    val id: Int,
    val images: List<CoverImage>
)

data class CoverImage(
    val id: Int,
    val type: ImagePurpose,
    val url: String
)

