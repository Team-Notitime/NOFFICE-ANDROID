package com.easyhz.noffice.data.announcement.repository.creation.place

import com.easyhz.noffice.core.common.di.Dispatcher
import com.easyhz.noffice.core.model.announcement.creation.place.OpenGraph
import com.easyhz.noffice.core.common.di.NofficeDispatchers.IO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import javax.inject.Inject

class PlaceRepositoryImpl @Inject constructor(
    @Dispatcher(IO) private val dispatcher: CoroutineDispatcher
) : PlaceRepository {
    override suspend fun fetchOpenGraphData(url: String): Result<OpenGraph> =
        withContext(dispatcher) {
            runCatching {
                val document = Jsoup.connect(url).get()
                val title = document.select("meta[property=og:title]").attr("content")
                val description = document.select("meta[property=og:description]").attr("content")
                val imageUrl = document.select("meta[property=og:image]").attr("content")
                val siteUrl = document.select("meta[property=og:url]").attr("content").takeIf { it.isNotBlank() } ?: url

                OpenGraph(title, description, imageUrl, siteUrl)
            }
        }
}