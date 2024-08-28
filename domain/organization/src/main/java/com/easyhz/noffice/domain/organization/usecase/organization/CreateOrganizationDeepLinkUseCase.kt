package com.easyhz.noffice.domain.organization.usecase.organization

import android.net.Uri
import com.easyhz.noffice.core.common.base.BaseUseCase
import com.easyhz.noffice.data.organization.repository.deepLink.DeepLinkRepository
import javax.inject.Inject

class CreateOrganizationDeepLinkUseCase @Inject constructor(
    private val deepLinkRepository: DeepLinkRepository
): BaseUseCase<Int, Uri>() {
    override suspend fun invoke(param: Int): Result<Uri> {
        return deepLinkRepository.createOrganizationDeepLink(param)
    }
}