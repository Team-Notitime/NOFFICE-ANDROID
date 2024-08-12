package com.easyhz.noffice.data.member.repository.user

import com.easyhz.noffice.core.datastore.datasource.user.UserLocalDataSource
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource
): UserRepository {
    override suspend fun getIsFirstRun(): Result<Boolean> {
        return userLocalDataSource.getFirstRun()
    }
}