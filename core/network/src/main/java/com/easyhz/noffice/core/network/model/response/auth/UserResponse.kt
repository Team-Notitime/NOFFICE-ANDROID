package com.easyhz.noffice.core.network.model.response.auth

data class UserResponse(
    val memberId: Int,
    val memberName: String,
    val provider: String,
    val token: TokenResponse
)