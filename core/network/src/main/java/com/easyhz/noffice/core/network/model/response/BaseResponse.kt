package com.easyhz.noffice.core.network.model.response


data class BaseResponse<T> (
    val timestamp: String,
    val httpStatus: Int,
    val code: String,
    val message: String,
    val data: T
)