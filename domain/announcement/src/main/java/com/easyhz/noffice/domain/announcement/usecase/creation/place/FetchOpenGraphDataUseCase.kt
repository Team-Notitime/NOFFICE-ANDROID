package com.easyhz.noffice.domain.announcement.usecase.creation.place

import com.easyhz.noffice.core.common.base.BaseUseCase
import com.easyhz.noffice.core.model.announcement.creation.place.OpenGraph
import com.easyhz.noffice.data.announcement.repository.creation.place.PlaceRepository
import javax.inject.Inject

class FetchOpenGraphDataUseCase @Inject constructor(
    private val placeRepository: PlaceRepository
): BaseUseCase<String, OpenGraph>() {
    override suspend fun invoke(param: String): Result<OpenGraph> =
        placeRepository.fetchOpenGraphData(param)
}