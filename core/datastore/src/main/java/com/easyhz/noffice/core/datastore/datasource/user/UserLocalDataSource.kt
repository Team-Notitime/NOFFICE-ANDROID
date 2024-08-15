package com.easyhz.noffice.core.datastore.datasource.user

interface UserLocalDataSource {
    suspend fun getFirstRun(): Result<Boolean>
    suspend fun updateFirstRun(newValue: Boolean)
}