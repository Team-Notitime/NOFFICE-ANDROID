package com.easyhz.noffice.domain.organization.usecase.image

import android.net.Uri
import com.easyhz.noffice.core.common.base.BaseUseCase
import com.easyhz.noffice.core.common.di.Dispatcher
import com.easyhz.noffice.core.common.di.NofficeDispatchers
import com.easyhz.noffice.core.common.util.Generate
import com.easyhz.noffice.core.model.image.ImageParam
import com.easyhz.noffice.core.model.image.ImagePurpose
import com.easyhz.noffice.core.model.image.ImageUrl
import com.easyhz.noffice.data.organization.repository.image.ImageRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UploadImageUseCase @Inject constructor(
    @Dispatcher(NofficeDispatchers.IO) private val dispatcher: CoroutineDispatcher,
    private val imageRepository: ImageRepository
) : BaseUseCase<ImageParam, String>() {
    override suspend fun invoke(param: ImageParam): Result<String> = withContext(dispatcher) {
        runCatching {
            val fileType = getFileType(param.uri)
            val fileName = param.uri.path + param.uri.port
            val imageUrl = fetchImageUrl(fileType, fileName, param.purpose)
            uploadImage(url = imageUrl.url, type = fileType, uri = param.uri)
            completeImageUpload(fileName)
            imageUrl.url.split("?")[0]
        }
    }

    private suspend fun getFileType(uri: Uri): String {
        return imageRepository.getMimeType(uri).getOrThrow().split("/")[1]
    }

    private suspend fun fetchImageUrl(type: String, name: String, purpose: ImagePurpose): ImageUrl {
        return imageRepository.fetchImageUrl(
            fileType = type,
            fileName = name,
            imagePurpose = purpose
        ).getOrThrow()
    }

    private suspend fun uploadImage(url: String, type: String, uri: Uri) {
        return imageRepository.uploadImage(url, type, uri).getOrThrow()
    }

    private suspend fun completeImageUpload(fileName: String) {
        imageRepository.completeImageUpload(fileName)
    }
}