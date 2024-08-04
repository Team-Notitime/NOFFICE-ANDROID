package com.easyhz.noffice.core.network.model.response

data class ErrorResponse(
    val timestamp: String,
    val httpStatus: Int,
    val code: String,
    val message: String,
)
