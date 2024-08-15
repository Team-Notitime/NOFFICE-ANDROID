package com.easyhz.noffice.data.organization.repository.image

import android.content.Context
import android.net.Uri
import com.easyhz.noffice.data.organization.provider.NofficeFileProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
): ImageRepository {
    override suspend fun getTakePictureUri(): Result<Uri> = NofficeFileProvider.getTakePictureUri(context)
}