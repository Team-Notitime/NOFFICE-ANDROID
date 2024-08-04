package com.easyhz.noffice.core.network.util

import com.easyhz.noffice.core.common.error.NofficeError
import com.easyhz.noffice.core.common.error.getErrorByStatusCode
import com.easyhz.noffice.core.network.converter.ErrorResponseConverter
import com.google.gson.Gson
import retrofit2.Response

fun <T> Response<T>.toResult(
    gson: Gson
): Result<T> {
    val body = this.body()
    val errorBody = this.errorBody()?.string()
    if (this.isSuccessful) {
        return if (body != null) {
            Result.success(body)
        } else {
            Result.failure(NofficeError.UnexpectedError)
        }
    }
    if (errorBody == null) {
        return Result.failure(NofficeError.UnexpectedError)
    }
    try {
        val errorResponse = ErrorResponseConverter(gson).fromJsonToErrorResponse(errorBody)

        val httpError = getErrorByStatusCode(
            statusCode = errorResponse.status,
            message = errorResponse.message
        )

        return Result.failure(httpError)
    } catch (e: Exception) {
        return  Result.failure(NofficeError.UnexpectedError)
    }
}