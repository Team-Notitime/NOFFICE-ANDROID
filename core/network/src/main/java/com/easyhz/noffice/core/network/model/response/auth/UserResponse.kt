package com.easyhz.noffice.core.network.model.response.auth

data class UserResponse(
    val memberId: String,
    val memberName: String,
    val provider: String,
    val token: TokenResponse
)