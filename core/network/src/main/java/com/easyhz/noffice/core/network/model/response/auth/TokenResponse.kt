package com.easyhz.noffice.core.network.model.response.auth

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String
)
