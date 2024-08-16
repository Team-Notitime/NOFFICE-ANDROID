package com.easyhz.noffice.data.organization.repository.image

import android.net.Uri
import com.easyhz.noffice.core.model.image.ImagePurpose
import com.easyhz.noffice.core.model.image.ImageUrl

interface ImageRepository {
    suspend fun getTakePictureUri(): Result<Uri>
    suspend fun fetchImageUrl(fileType: String, fileName: String, imagePurpose: ImagePurpose): Result<ImageUrl>
    suspend fun getMimeType(uri: Uri): Result<String>
    suspend fun uploadImage(url: String, fileType: String, uri: Uri): Result<Unit>
    suspend fun completeImageUpload(fileName: String): Result<Unit>
}