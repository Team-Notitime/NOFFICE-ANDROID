package com.easyhz.noffice.data.auth.repository.login

import android.content.Context

interface LoginRepository {
    suspend fun login(context: Context, provider: String): Result<Unit>
}