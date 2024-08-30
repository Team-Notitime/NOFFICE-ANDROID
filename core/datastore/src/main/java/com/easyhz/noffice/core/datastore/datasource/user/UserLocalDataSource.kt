package com.easyhz.noffice.core.datastore.datasource.user

interface UserLocalDataSource {
    suspend fun getFirstRun(): Result<Boolean>
    suspend fun updateFirstRun(newValue: Boolean)
    suspend fun getMemberId(): Result<Int>
    suspend fun updateMemberId(newValue: Int)
    suspend fun deleteMemberId()
    suspend fun getMemberName(): Result<String>
    suspend fun updateMemberName(newValue: String)
    suspend fun deleteMemberName()
}