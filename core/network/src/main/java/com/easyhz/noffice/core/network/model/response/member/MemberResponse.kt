package com.easyhz.noffice.core.network.model.response.member

data class MemberResponse(
    val alias: String,
    val id: Int,
    val name: String,
    val profileImage: String?
)