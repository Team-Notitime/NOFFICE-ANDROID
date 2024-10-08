package com.easyhz.noffice.core.network.api.image

import com.easyhz.noffice.core.network.model.request.image.ImageRequest
import com.easyhz.noffice.core.network.model.request.image.ProfileImageRequest
import com.easyhz.noffice.core.network.model.response.image.ImageUrlResponse
import com.easyhz.noffice.core.network.util.NofficeResult
import retrofit2.http.Body
import retrofit2.http.DELETE
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

    /* 회원 프로필 사진 이미지 변경 */
    @PATCH("/api/v1/member/profile-image")
    suspend fun updateMemberProfileImage(
        @Body request: ProfileImageRequest
    ): NofficeResult<Unit>

    /* 조직 프로필 이미지 삭제 */
    @DELETE("/api/v1/organizations/{organizationId}/profile-image")
    suspend fun deleteOrganizationProfileImage(
        @Path("organizationId") organizationId: Int
    ): NofficeResult<Unit>

}