package com.easyhz.noffice.data.member.repository.user

import com.easyhz.noffice.core.datastore.datasource.user.UserLocalDataSource
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource
): UserRepository {
    override suspend fun getIsFirstRun(): Result<Boolean> {
        return userLocalDataSource.getFirstRun()
    }

    override suspend fun setIsFirstRun(newValue: Boolean): Result<Unit> = runCatching {
        return@runCatching userLocalDataSource.updateFirstRun(newValue)
    }
}