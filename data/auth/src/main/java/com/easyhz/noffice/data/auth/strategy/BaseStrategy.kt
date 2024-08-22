package com.easyhz.noffice.data.auth.strategy

import android.content.Context
import android.util.Log
import com.easyhz.noffice.core.common.error.NofficeError

abstract class BaseStrategy {

    abstract suspend fun login(context: Context): Result<String>

    abstract suspend fun logout(context: Context): Result<Unit>

    fun throwUnexpectedError(tag: String = "BaseStrategy", message: String) {
        Log.e(tag, message)
        throw NofficeError.UnexpectedError
    }
}