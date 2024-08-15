package com.easyhz.noffice.data.announcement.repository.creation.place

import com.easyhz.noffice.core.model.announcement.creation.place.OpenGraph

interface PlaceRepository {
    suspend fun fetchOpenGraphData(url: String): Result<OpenGraph>
}