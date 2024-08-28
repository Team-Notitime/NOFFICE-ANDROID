package com.easyhz.noffice.domain.organization.usecase.image

import com.easyhz.noffice.core.common.base.BaseUseCase
import com.easyhz.noffice.core.model.image.ProfileImageParam
import com.easyhz.noffice.data.organization.repository.image.ImageRepository
import javax.inject.Inject

class UpdateOrganizationProfileImageUseCase @Inject constructor(
    private val imageRepository: ImageRepository,
    private val uploadImageUseCase: UploadImageUseCase,
) : BaseUseCase<ProfileImageParam, String>() {
    override suspend fun invoke(param: ProfileImageParam): Result<String> = runCatching {
        val url = uploadImageUseCase.invoke(param.imageParam).getOrThrow()
        imageRepository.updateOrganizationProfileImage(param.organizationId, url)
        url
    }
}