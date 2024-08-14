package com.easyhz.noffice.data.member.repository.user

interface UserRepository {
    suspend fun getIsFirstRun(): Result<Boolean>
    suspend fun setIsFirstRun(newValue: Boolean): Result<Unit>
    suspend fun getMemberId(): Result<String>
    suspend fun setMemberId(newValue: String): Result<Unit>
}