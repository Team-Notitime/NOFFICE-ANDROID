package com.easyhz.noffice.data.notification.repository.messaging

import com.easyhz.noffice.core.common.di.Dispatcher
import com.easyhz.noffice.core.common.di.NofficeDispatchers
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CloudMessagingRepositoryImpl @Inject constructor(
    @Dispatcher(NofficeDispatchers.IO) private val dispatcher: CoroutineDispatcher,
    private val messagingInstance: FirebaseMessaging
): CloudMessagingRepository {
    override suspend fun getToken(): Result<String> = withContext(dispatcher) {
        runCatching {
            messagingInstance.token.await()
        }
    }
}