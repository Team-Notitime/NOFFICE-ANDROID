package com.easyhz.noffice.data.organization.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.easyhz.noffice.core.network.api.organization.OrganizationService
import com.easyhz.noffice.core.network.model.response.organization.OrganizationResponse

class OrganizationPagingSource(
    private val organizationService: OrganizationService,
    private val memberId: Int?
):PagingSource<Int, OrganizationResponse>() {
    override fun getRefreshKey(state: PagingState<Int, OrganizationResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.run {
                prevKey?.plus(1) ?: nextKey?.minus(1)
            }
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, OrganizationResponse> {
        val page = params.key ?: START_PAGE
        val loadSize = params.loadSize
        return memberId?.let { id ->
            organizationService.fetchOrganizations(
                memberId = id,
                page = page,
                size = loadSize,
                sort = emptyList() /* FIXME */
            ).fold(
                onSuccess = {
                    LoadResult.Page(
                        data = it.data.content,
                        prevKey = null,
                        nextKey = if (it.data.content.size < loadSize) null else page + 1
                    )
                },
                onFailure = {
                    LoadResult.Error(it)
                }
            )
        } ?: LoadResult.Page(data = emptyList(), prevKey = null, nextKey = null)
    }
    companion object {
        const val PAGE_SIZE = 10
        private const val START_PAGE = 0
    }
}