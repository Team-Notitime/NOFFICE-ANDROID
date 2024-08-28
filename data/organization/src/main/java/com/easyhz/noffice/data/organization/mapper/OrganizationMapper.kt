package com.easyhz.noffice.data.organization.mapper

import com.easyhz.noffice.core.model.image.ImagePurpose
import com.easyhz.noffice.core.model.organization.CoverImage
import com.easyhz.noffice.core.model.organization.JoinStatus
import com.easyhz.noffice.core.model.organization.Organization
import com.easyhz.noffice.core.model.organization.OrganizationInformation
import com.easyhz.noffice.core.model.organization.OrganizationJoin
import com.easyhz.noffice.core.model.organization.OrganizationSignUpInformation
import com.easyhz.noffice.core.model.organization.SelectableCover
import com.easyhz.noffice.core.model.organization.member.MemberType
import com.easyhz.noffice.core.model.organization.member.mapMemberCounts
import com.easyhz.noffice.core.model.organization.param.RegisterMemberParam
import com.easyhz.noffice.core.network.model.request.organization.RegisterMemberRequest
import com.easyhz.noffice.core.network.model.response.organization.ImageResponse
import com.easyhz.noffice.core.network.model.response.organization.OrganizationCapsuleResponse
import com.easyhz.noffice.core.network.model.response.organization.OrganizationInformationResponse
import com.easyhz.noffice.core.network.model.response.organization.OrganizationJoinResponse
import com.easyhz.noffice.core.network.model.response.organization.OrganizationResponse
import com.easyhz.noffice.core.network.model.response.organization.OrganizationSignUpInformationResponse
import com.easyhz.noffice.core.network.model.response.organization.SelectableCoverResponse

internal fun OrganizationResponse.toModel(): Organization = Organization(
    id = this.id,
    name = this.name,
    profileImageUrl = this.profileImage,
    role = MemberType.valueOf(this.role ?: MemberType.PARTICIPANT.name),
    joinStatus = null
)

internal fun OrganizationInformationResponse.toModel(): OrganizationInformation = OrganizationInformation(
    id = this.organizationId,
    name = this.organizationName,
    category = this.categories,
    profileImageUrl = this.profileImage,
    members = mapMemberCounts(MemberType.LEADER to leaderCount, MemberType.PARTICIPANT to participantCount),
    hasStandbyMember = this.isPending,
    role = MemberType.valueOf(this.role)
)

fun OrganizationCapsuleResponse.toModel(): Organization = Organization(
    id = this.organizationId,
    name = this.organizationName,
    profileImageUrl = this.profileImage,
    role = MemberType.valueOf(this.role),
    joinStatus = JoinStatus.valueOf(this.joinStatus)
)

internal fun OrganizationJoinResponse.toModel(): OrganizationJoin = OrganizationJoin(
    id = this.organizationId,
    name = this.organizationName,
    memberId = this.memberId
)

internal fun OrganizationSignUpInformationResponse.toModel(): OrganizationSignUpInformation = OrganizationSignUpInformation(
    organizationId = this.organizationId,
    organizationName = this.organizationName,
    profileImage = this.profileImage
)

internal fun RegisterMemberParam.toRequest(): RegisterMemberRequest = RegisterMemberRequest(
    role = this.role.name,
    memberIds = this.memberIds
)

internal fun SelectableCoverResponse.toModel(): SelectableCover = SelectableCover(
    id = this.id,
    images = this.images.map { it.toModel() }
)

internal fun ImageResponse.toModel(): CoverImage = CoverImage(
    id = this.id,
    type = ImagePurpose.valueOf(this.type),
    url = this.url
)