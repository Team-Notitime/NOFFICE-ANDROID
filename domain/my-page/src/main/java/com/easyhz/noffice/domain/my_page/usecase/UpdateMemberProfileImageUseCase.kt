package com.easyhz.noffice.domain.my_page.usecase

import com.easyhz.noffice.core.common.base.BaseUseCase
import com.easyhz.noffice.core.model.image.ImageParam
import com.easyhz.noffice.data.organization.repository.image.ImageRepository
import com.easyhz.noffice.domain.organization.usecase.image.UploadImageUseCase
import javax.inject.Inject

class UpdateMemberProfileImageUseCase @Inject constructor(
    private val imageRepository: ImageRepository,
    private val uploadImageUseCase: UploadImageUseCase
): BaseUseCase<ImageParam, String>() {
    override suspend fun invoke(param: ImageParam): Result<String> = runCatching {
        val url = uploadImageUseCase.invoke(param).getOrThrow()
        imageRepository.updateMemberProfileImage(url)
        url
    }
}