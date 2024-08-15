package com.easyhz.noffice.core.network.interceptor

import com.easyhz.noffice.core.datastore.datasource.auth.AuthLocalDataSource
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val authLocalDataSource: AuthLocalDataSource
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val accessToken = runBlocking {
            authLocalDataSource.getAccessToken()
        }

        val newRequest = originalRequest.newBuilder()
            .addHeader("Authorization", "Bearer ${accessToken.getOrNull()}")
            .build()

        return chain.proceed(newRequest)
    }
}