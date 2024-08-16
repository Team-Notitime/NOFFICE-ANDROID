package com.easyhz.noffice.data.organization.mapper

import com.easyhz.noffice.core.model.organization.Organization
import com.easyhz.noffice.core.model.organization.OrganizationInformation
import com.easyhz.noffice.core.model.organization.member.MemberType
import com.easyhz.noffice.core.model.organization.member.mapMemberCounts
import com.easyhz.noffice.core.network.model.response.organization.OrganizationCapsuleResponse
import com.easyhz.noffice.core.network.model.response.organization.OrganizationInformationResponse
import com.easyhz.noffice.core.network.model.response.organization.OrganizationResponse

internal fun OrganizationResponse.toModel(): Organization = Organization(
    id = this.id,
    name = this.name,
    profileImageUrl = this.profileImage
)

internal fun OrganizationInformationResponse.toModel(): OrganizationInformation = OrganizationInformation(
    id = this.organizationId,
    name = this.organizationName,
    category = this.categories,
    profileImageUrl = this.profileImage,
    members = mapMemberCounts(MemberType.LEADER to leaderCount, MemberType.MEMBER to participantCount),
    hasStandbyMember = this.isPending
)

internal fun OrganizationCapsuleResponse.toModel(): Organization = Organization(
    id = this.organizationId,
    name = this.organizationName,
    profileImageUrl = this.profileImage
)