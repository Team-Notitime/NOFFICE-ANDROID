package com.easyhz.noffice.data.auth.repository.auth

import android.content.Context

interface AuthRepository {
    suspend fun login(context: Context, provider: String): Result<Unit>
    suspend fun logout(context: Context): Result<Unit>
    suspend fun withdraw(context: Context): Result<Unit>
}