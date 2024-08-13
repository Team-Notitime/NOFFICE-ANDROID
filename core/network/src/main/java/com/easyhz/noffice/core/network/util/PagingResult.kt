package com.easyhz.noffice.core.network.util

import com.easyhz.noffice.core.common.error.NofficeError
import com.easyhz.noffice.core.common.error.getErrorByStatusCode
import com.easyhz.noffice.core.network.model.response.base.BaseResponse
import com.easyhz.noffice.core.network.model.response.paging.PagingResponse

/* Result<BaseResponse<PagingResponse<T>>> */
typealias PagingResult<T> = NofficeResult<PagingResponse<T>>

fun <T> PagingResult<T>.toResult() =
    this.fold(
        onSuccess = { response ->
            handleResponse(response)
        },
        onFailure = { exception ->
            Result.failure(exception)
        }
    )

private fun <T> handleResponse(response: BaseResponse<PagingResponse<T>>): Result<List<T>> =
    when (response.httpStatus) {
        in 200..300 -> { Result.success(response.data.content) }
        in 300..600 -> { throw getErrorByStatusCode(response.httpStatus, response.message) }
        else -> { throw NofficeError.UnexpectedError }
    }