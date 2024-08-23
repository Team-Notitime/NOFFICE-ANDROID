package com.easyhz.noffice.data.announcement.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.easyhz.noffice.core.network.api.task.TaskService
import com.easyhz.noffice.core.network.model.response.task.AssignedTaskResponse

class AssignedTaskPagingSource(
    private val taskService: TaskService
): PagingSource<Int, AssignedTaskResponse>() {
    override fun getRefreshKey(state: PagingState<Int, AssignedTaskResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.run {
                prevKey?.plus(1) ?: nextKey?.minus(1)
            }
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AssignedTaskResponse> {
        val page = params.key ?: START_PAGE
        val loadSize = params.loadSize
        return taskService.fetchAssignedTasks(
            page = page,
            size = loadSize,
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