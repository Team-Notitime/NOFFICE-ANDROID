package com.easyhz.noffice.core.network.model.response.member

data class OrganizationMemberResponse(
    val requester: MemberResponse,
    val leaders: List<MemberResponse>,
    val participants: List<MemberResponse>,
)