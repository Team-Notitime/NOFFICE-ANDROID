package com.easyhz.noffice.domain.notification.usecase

import com.easyhz.noffice.core.common.base.BaseUseCase
import com.easyhz.noffice.data.notification.repository.messaging.CloudMessagingRepository
import javax.inject.Inject

class GetMessagingTokenUseCase @Inject constructor(
    private val cloudMessagingRepository: CloudMessagingRepository
): BaseUseCase<Unit, String>() {
    override suspend fun invoke(param: Unit): Result<String> {
        return cloudMessagingRepository.getToken()
    }
}