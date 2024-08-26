package com.easyhz.noffice.data.organization.repository.promotion

interface PromotionRepository {
    suspend fun verifyPromotion(promotionCode: String): Result<Boolean>
}