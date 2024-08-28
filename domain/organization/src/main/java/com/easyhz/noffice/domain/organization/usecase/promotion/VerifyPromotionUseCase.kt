package com.easyhz.noffice.domain.organization.usecase.promotion

import com.easyhz.noffice.core.common.base.BaseUseCase
import com.easyhz.noffice.data.organization.repository.promotion.PromotionRepository
import javax.inject.Inject

class VerifyPromotionUseCase @Inject constructor(
    private val promotionRepository: PromotionRepository
): BaseUseCase<String, Boolean>() {
    override suspend fun invoke(param: String): Result<Boolean> {
        return promotionRepository.verifyPromotion(param)
    }
}