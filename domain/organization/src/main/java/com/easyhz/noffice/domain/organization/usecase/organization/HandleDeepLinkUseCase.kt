package com.easyhz.noffice.domain.organization.usecase.organization

import android.content.Intent
import com.easyhz.noffice.core.common.base.BaseUseCase
import com.easyhz.noffice.data.organization.repository.deepLink.DeepLinkRepository
import javax.inject.Inject

class HandleDeepLinkUseCase @Inject constructor(
    private val deepLinkRepository: DeepLinkRepository
): BaseUseCase<Intent, Int>() {
    override suspend fun invoke(param: Intent): Result<Int> {
        return deepLinkRepository.handleDeepLink(param)
    }
}