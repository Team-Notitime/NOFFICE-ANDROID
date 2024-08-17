package com.easyhz.noffice.core.network.model.response.paging

data class Pageable(
    val offset: Int,
    val pageNumber: Int,
    val pageSize: Int,
    val paged: Boolean,
//    val sort: List<SortX>,
    val unpaged: Boolean
)