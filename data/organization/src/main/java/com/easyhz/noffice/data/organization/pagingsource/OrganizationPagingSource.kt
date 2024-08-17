package com.easyhz.noffice.data.organization.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.easyhz.noffice.core.network.api.organization.OrganizationService
import com.easyhz.noffice.core.network.model.response.organization.OrganizationCapsuleResponse

class OrganizationPagingSource(
    private val organizationService: OrganizationService,
): PagingSource<Int, OrganizationCapsuleResponse>() {
    override fun getRefreshKey(state: PagingState<Int, OrganizationCapsuleResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.run {
                prevKey?.plus(1) ?: nextKey?.minus(1)
            }
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, OrganizationCapsuleResponse> {
        val page = params.key ?: START_PAGE
        val loadSize = params.loadSize
        return organizationService.fetchOrganizations(
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
    }

    companion object {
        const val PAGE_SIZE = 10
        private const val START_PAGE = 0
    }
}