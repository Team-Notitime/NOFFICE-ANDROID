package com.easyhz.noffice.core.network.api.promotion

import com.easyhz.noffice.core.network.model.request.promotion.PromotionRequest
import com.easyhz.noffice.core.network.util.NofficeResult
import retrofit2.http.GET
import retrofit2.http.Query

interface PromotionService {
    /* 프로모션 코드 검증 */
    @GET("/api/v1/promotion/verify")
    suspend fun verifyPromotion(
        @Query("promotionCode") promotionCode: PromotionRequest
    ): NofficeResult<Boolean>
}