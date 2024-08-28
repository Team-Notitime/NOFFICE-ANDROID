package com.easyhz.noffice.data.member.repository.user

import com.easyhz.noffice.core.common.di.Dispatcher
import com.easyhz.noffice.core.common.di.NofficeDispatchers
import com.easyhz.noffice.core.datastore.datasource.user.UserLocalDataSource
import com.easyhz.noffice.core.model.auth.UserInfo
import com.easyhz.noffice.core.network.api.auth.AuthService
import com.easyhz.noffice.core.network.model.request.member.AliasRequest
import com.easyhz.noffice.core.network.util.toResult
import com.easyhz.noffice.data.member.mapper.toModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    @Dispatcher(NofficeDispatchers.IO) private val dispatcher: CoroutineDispatcher,
    private val userLocalDataSource: UserLocalDataSource,
    private val authService: AuthService,
): UserRepository {
    override suspend fun getIsFirstRun(): Result<Boolean> {
        return userLocalDataSource.getFirstRun()
    }

    override suspend fun setIsFirstRun(newValue: Boolean): Result<Unit> = runCatching {
        return@runCatching userLocalDataSource.updateFirstRun(newValue)
    }

    override suspend fun getMemberId(): Result<Int> {
        return userLocalDataSource.getMemberId()
    }

    override suspend fun setMemberId(newValue: Int): Result<Unit> = runCatching {
        return@runCatching userLocalDataSource.updateMemberId(newValue)
    }

    override suspend fun fetchUserInfo(): Result<UserInfo> = withContext(dispatcher) {
        return@withContext authService.fetchUserInfo().toResult().map { it.toModel() }
    }

    override suspend fun updateUserAlias(alias: String): Result<Unit> = withContext(dispatcher) {
        return@withContext authService.updateUserAlias(AliasRequest(alias)).toResult()
    }
}