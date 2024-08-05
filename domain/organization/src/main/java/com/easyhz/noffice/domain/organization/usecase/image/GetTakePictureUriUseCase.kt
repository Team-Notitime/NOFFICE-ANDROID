package com.easyhz.noffice.domain.organization.usecase.image

import android.net.Uri
import com.easyhz.noffice.core.common.base.BaseUseCase
import com.easyhz.noffice.data.organization.repository.image.ImageRepository
import javax.inject.Inject

class GetTakePictureUriUseCase @Inject constructor(
    private val imageRepository: ImageRepository
): BaseUseCase<Unit, Uri>() {
    override suspend fun invoke(param: Unit): Result<Uri> =
        imageRepository.getTakePictureUri()
}