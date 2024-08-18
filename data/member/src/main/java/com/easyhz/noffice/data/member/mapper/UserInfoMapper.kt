package com.easyhz.noffice.data.member.mapper

import com.easyhz.noffice.core.model.auth.UserInfo
import com.easyhz.noffice.core.network.model.response.auth.UserInfoResponse
import com.easyhz.noffice.data.organization.mapper.toModel

internal fun UserInfoResponse.toModel(): UserInfo = UserInfo(
    alias = this.alias,
    id = this.id,
    name = this.name,
    profileImage = this.profileImage,
    organizations = this.organizations.map { it.toModel() }
)