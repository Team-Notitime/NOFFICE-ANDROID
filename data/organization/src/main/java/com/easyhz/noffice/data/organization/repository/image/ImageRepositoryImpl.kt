package com.easyhz.noffice.data.organization.repository.image

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import com.easyhz.noffice.core.common.di.Dispatcher
import com.easyhz.noffice.core.common.di.NofficeDispatchers
import com.easyhz.noffice.core.model.image.ImagePurpose
import com.easyhz.noffice.core.model.image.ImageUrl
import com.easyhz.noffice.core.network.api.image.ImageService
import com.easyhz.noffice.core.network.model.request.image.ImageRequest
import com.easyhz.noffice.core.network.model.request.image.ProfileImageRequest
import com.easyhz.noffice.core.network.uploader.ImageUploader
import com.easyhz.noffice.core.network.util.toResult
import com.easyhz.noffice.data.organization.mapper.toModel
import com.easyhz.noffice.data.organization.provider.NofficeFileProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    @Dispatcher(NofficeDispatchers.IO) private val dispatcher: CoroutineDispatcher,
    @ApplicationContext private val context: Context,
    private val imageService: ImageService,
    private val imageUploader: ImageUploader,
) : ImageRepository {
    override suspend fun getTakePictureUri(): Result<Uri> =
        NofficeFileProvider.getTakePictureUri(context)

    override suspend fun fetchImageUrl(
        fileType: String,
        fileName: String,
        imagePurpose: ImagePurpose
    ): Result<ImageUrl> = withContext(dispatcher) {
        imageService.fetchImageUrl(
            fileType = fileType,
            fileName = fileName,
            purpose = imagePurpose.name
        ).map { it.toModel() }
    }

    override suspend fun getMimeType(uri: Uri): Result<String> {
        return NofficeFileProvider.getMimeType(context, uri)
    }

    override suspend fun uploadImage(url: String, fileType: String, uri: Uri): Result<Unit> =
        withContext(dispatcher) {
            return@withContext imageUploader.uploadImage(context, url, fileType, uri)
        }

    override suspend fun completeImageUpload(fileName: String): Result<Unit> =
        withContext(dispatcher) {
            return@withContext imageService.completeImageUpload(ImageRequest(fileName))
        }

    override suspend fun getDrawableUri(drawableId: Int): Result<Uri> {
       return kotlin.runCatching {
           drawableId.getResourceUri(context)
       }
    }

    override suspend fun updateOrganizationProfileImage(
        organizationId: Int,
        imageUrl: String
    ): Result<Unit> = withContext(dispatcher) {
        return@withContext imageService.updateOrganizationProfileImage(
            organizationId = organizationId,
            request = ProfileImageRequest(imageUrl = imageUrl)
        ).toResult()
    }

    private fun Int.getResourceUri(context: Context): Uri {
        return context.resources.let {
            Uri.Builder()
                .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
                .authority(it.getResourcePackageName(this))
                .appendPath(it.getResourceTypeName(this))
                .appendPath(it.getResourceEntryName(this))
                .build()
        }
    }
}