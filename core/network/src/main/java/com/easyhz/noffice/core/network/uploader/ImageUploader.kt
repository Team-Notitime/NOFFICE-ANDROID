package com.easyhz.noffice.core.network.uploader

import android.content.Context
import android.net.Uri
import com.easyhz.noffice.core.network.di.PureClient
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException
import javax.inject.Inject

class ImageUploader @Inject constructor(
    @PureClient private val client: OkHttpClient
) {
    fun uploadImage(context: Context, url: String, mimeType: String, uri: Uri): Result<Unit> {
        return runCatching {
            val contentResolver = context.contentResolver
            val inputStream = contentResolver.openInputStream(uri) ?: throw IOException("Failed to open file input stream")
            val fileBytes = inputStream.readBytes()
            inputStream.close()

            val fileRequestBody = fileBytes.toRequestBody(mimeType.toMediaTypeOrNull())

            val request = Request.Builder()
                .url(url)
                .put(fileRequestBody)
                .build()

            val response = client.newCall(request).execute()

            if (response.isSuccessful) {
                Unit
            } else {
                throw Exception("Failed to upload file: ${response.code}")
            }
        }
    }
}