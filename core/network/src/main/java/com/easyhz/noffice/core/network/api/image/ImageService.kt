package com.easyhz.noffice.core.network.api.image

import com.easyhz.noffice.core.network.model.request.image.ImageRequest
import com.easyhz.noffice.core.network.model.request.image.ProfileImageRequest
import com.easyhz.noffice.core.network.model.response.image.ImageUrlResponse
import com.easyhz.noffice.core.network.util.NofficeResult
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ImageService {

    @GET("/api/v1/image")
    suspend fun fetchImageUrl(
        @Query("fileType") fileType: String,
        @Query("fileName") fileName: String,
        @Query("imagePurpose") purpose: String
    ): Result<ImageUrlResponse>

    @POST("/api/v1/image")
    suspend fun completeImageUpload(
        @Body request: ImageRequest
    ): Result<Unit>

    /* 조직 프로필 이미지 수정 */
    @PATCH("/api/v1/organizations/{organizationId}/profile-image")
    suspend fun updateOrganizationProfileImage(
        @Path("organizationId") organizationId: Int,
        @Body request: ProfileImageRequest
    ): NofficeResult<Unit>
}