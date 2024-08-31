package com.easyhz.noffice.data.organization.repository.promotion

import com.easyhz.noffice.core.common.di.Dispatcher
import com.easyhz.noffice.core.common.di.NofficeDispatchers
import com.easyhz.noffice.core.network.api.promotion.PromotionService
import com.easyhz.noffice.core.network.model.request.promotion.PromotionRequest
import com.easyhz.noffice.core.network.util.toResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PromotionRepositoryImpl @Inject constructor(
    @Dispatcher(NofficeDispatchers.IO) private val dispatcher: CoroutineDispatcher,
    private val promotionService: PromotionService
) : PromotionRepository {
    override suspend fun verifyPromotion(promotionCode: String): Result<Boolean> =
        withContext(dispatcher) {
            promotionService.verifyPromotion(PromotionRequest(promotionCode)).toResult()
        }
}