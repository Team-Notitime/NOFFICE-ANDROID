package com.easyhz.noffice.core.model.image

import android.net.Uri

data class ImageParam(
    val uri: Uri,
    val purpose: ImagePurpose
)