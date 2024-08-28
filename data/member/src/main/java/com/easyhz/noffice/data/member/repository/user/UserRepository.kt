package com.easyhz.noffice.data.member.repository.user

import com.easyhz.noffice.core.model.auth.UserInfo

interface UserRepository {
    suspend fun getIsFirstRun(): Result<Boolean>
    suspend fun setIsFirstRun(newValue: Boolean): Result<Unit>
    suspend fun getMemberId(): Result<Int>
    suspend fun setMemberId(newValue: Int): Result<Unit>
    suspend fun fetchUserInfo(): Result<UserInfo>
    suspend fun updateUserAlias(alias: String): Result<Unit>
}