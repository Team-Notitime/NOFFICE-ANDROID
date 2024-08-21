package com.easyhz.noffice.core.network.model.request.organization

data class RegisterMemberRequest(
    val role: String,
    val memberIds: List<Int>
)
