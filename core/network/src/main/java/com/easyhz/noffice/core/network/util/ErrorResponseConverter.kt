package com.easyhz.noffice.core.network.util

import com.easyhz.noffice.core.network.model.response.ErrorResponse
import com.google.gson.Gson
import javax.inject.Inject

class ErrorResponseConverter @Inject constructor(
    private val gson: Gson
) {
    fun fromJsonToErrorResponse(json: String): ErrorResponse {
        return gson.fromJson(json, ErrorResponse::class.java)
    }
}