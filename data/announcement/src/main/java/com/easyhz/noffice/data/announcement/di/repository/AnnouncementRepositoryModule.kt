package com.easyhz.noffice.data.announcement.di.repository

import com.easyhz.noffice.data.announcement.repository.announcement.AnnounceRepository
import com.easyhz.noffice.data.announcement.repository.announcement.AnnounceRepositoryImpl
import com.easyhz.noffice.data.announcement.repository.task.TaskRepository
import com.easyhz.noffice.data.announcement.repository.task.TaskRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AnnouncementRepositoryModule {
    @Binds
    fun bindAnnouncementRepository(
        announceRepositoryImpl: AnnounceRepositoryImpl
    ): AnnounceRepository

    @Binds
    fun bindTaskRepository(
        taskRepositoryImpl: TaskRepositoryImpl
    ): TaskRepository
}