package com.easyhz.noffice.domain.organization.usecase.image

import com.easyhz.noffice.core.common.base.BaseUseCase
import com.easyhz.noffice.data.organization.repository.image.ImageRepository
import javax.inject.Inject

class DeleteOrganizationProfileImageUseCase @Inject constructor(
    private val imageRepository: ImageRepository
): BaseUseCase<Int, Unit>() {
    override suspend fun invoke(param: Int): Result<Unit> {
        return imageRepository.deleteOrganizationProfileImage(param)
    }
}