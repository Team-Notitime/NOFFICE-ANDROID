package com.easyhz.noffice.data.organization.mapper

import com.easyhz.noffice.core.model.common.Member
import com.easyhz.noffice.core.model.organization.member.MemberType
import com.easyhz.noffice.core.network.model.response.member.MemberResponse
import com.easyhz.noffice.core.network.model.response.member.OrganizationMemberResponse

internal fun OrganizationMemberResponse.toModel(): List<Member> {
    return listOfNotNull(requester.toModelWithRole(MemberType.LEADER))
        .plus(leaders.map { it.toModelWithRole(MemberType.LEADER) })
        .plus(participants.map { it.toModelWithRole(MemberType.PARTICIPANT) })
}


private fun MemberResponse.toModelWithRole(role: MemberType): Member {
    return Member(
        alias = this.alias,
        id = this.id,
        name = this.name,
        profileImage = this.profileImage,
        role = role,
        isSelected = false
    )
}