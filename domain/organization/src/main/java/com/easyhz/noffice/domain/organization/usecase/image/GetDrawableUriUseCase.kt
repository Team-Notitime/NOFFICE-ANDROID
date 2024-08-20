package com.easyhz.noffice.domain.organization.usecase.image

import android.net.Uri
import com.easyhz.noffice.core.common.base.BaseUseCase
import com.easyhz.noffice.data.organization.repository.image.ImageRepository
import javax.inject.Inject

class GetDrawableUriUseCase @Inject constructor(
    private val imageRepository: ImageRepository
): BaseUseCase<Int, Uri>() {
    override suspend fun invoke(param: Int): Result<Uri> {
        return imageRepository.getDrawableUri(param)
    }
}