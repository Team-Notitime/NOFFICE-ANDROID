package com.easyhz.noffice.core.network.model.request.sign

data class LoginRequest(
    val code: String,
    val provider: String
)
