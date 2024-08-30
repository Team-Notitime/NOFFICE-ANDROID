package com.easyhz.noffice.data.auth.repository.auth

import android.content.Context
import com.easyhz.noffice.core.model.auth.UserType

interface AuthRepository {
    suspend fun login(context: Context, provider: String): Result<UserType>
    suspend fun logout(context: Context): Result<Unit>
    suspend fun withdraw(context: Context): Result<Unit>
}