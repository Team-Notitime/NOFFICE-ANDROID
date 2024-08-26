package com.easyhz.noffice.core.network.model.response.organization

data class SelectableCoverResponse(
    val id: Int,
    val images: List<ImageResponse>
)


data class ImageResponse(
    val id: Int,
    val type: String,
    val url: String
)