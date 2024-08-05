package com.easyhz.noffice.data.organization.repository.image

import android.net.Uri

interface ImageRepository {
    suspend fun getTakePictureUri(): Result<Uri>
}