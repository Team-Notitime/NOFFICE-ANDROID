package com.easyhz.noffice.data.auth.strategy

import android.content.Context

interface BaseStrategy {

    suspend fun login(context: Context): Result<String>

    suspend fun logout()
}