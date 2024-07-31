package com.easyhz.noffice.core.common.error

import androidx.annotation.StringRes
import com.easyhz.noffice.core.common.R

@StringRes
private fun Throwable.handleError(): Int {
    return when(this) {
        is NofficeError.UnexpectedError -> R.string.error_unexpected
        is NofficeError.NetworkConnectionError -> R.string.error_network_connection
        is HttpError.BadRequestError -> R.string.error_bad_request
        is HttpError.UnauthorizedError -> R.string.error_unauthorized
        is HttpError.ForbiddenError -> R.string.error_forbidden
        is HttpError.NotFoundError -> R.string.error_not_found
        is HttpError.InternalServerError -> R.string.error_internal_server
        else -> R.string.error_unexpected
    }
}