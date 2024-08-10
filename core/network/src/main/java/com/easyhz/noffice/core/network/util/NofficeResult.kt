package com.easyhz.noffice.core.network.util

import com.easyhz.noffice.core.common.error.NofficeError
import com.easyhz.noffice.core.common.error.getErrorByStatusCode
import com.easyhz.noffice.core.network.model.response.base.BaseResponse

typealias NofficeResult<T> = kotlin.Result<BaseResponse<T>>

fun <T> NofficeResult<T>.toResult() =
    this.fold(
        onSuccess = { baseResponse ->
            handleBaseResponse(baseResponse)
        },
        onFailure = { exception ->
            Result.failure(exception)
        }
    )

private fun <T> handleBaseResponse(baseResponse: BaseResponse<T>): Result<T> =
    when(baseResponse.httpStatus) {
        in 200 .. 300 -> { Result.success(baseResponse.data)}
        in 300 .. 600 -> { throw getErrorByStatusCode(baseResponse.httpStatus, baseResponse.message)
        }
        else -> { throw NofficeError.UnexpectedError }
    }
