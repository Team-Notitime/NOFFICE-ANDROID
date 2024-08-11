package com.easyhz.noffice.data.auth.strategy

import android.content.Context
import com.easyhz.noffice.data.auth.util.Provider
import javax.inject.Inject

class AuthStrategyContext @Inject constructor(
    private val strategies: Map<String, @JvmSuppressWildcards BaseStrategy>
) {
    private var currentStrategy: BaseStrategy = strategies[Provider.GOOGLE.name]!!

    fun setStrategy(provider: String) {
        currentStrategy = strategies[provider] ?: throw IllegalArgumentException("Invalid provider")
    }

    suspend fun login(context: Context): Result<String> {
        return currentStrategy.login(context)
    }

    suspend fun logout(context: Context): Result<Unit> {
        return currentStrategy.logout(context)
    }
}