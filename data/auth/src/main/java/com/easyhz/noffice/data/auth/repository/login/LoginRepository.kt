package com.easyhz.noffice.data.auth.repository.login

import android.content.Context

interface LoginRepository {
    suspend fun loginWithGoogle(context: Context): Result<Unit>
}