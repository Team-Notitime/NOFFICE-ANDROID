package com.easyhz.noffice.data.auth.mapper.auth

import com.easyhz.noffice.core.model.auth.UserType
import com.easyhz.noffice.core.network.model.response.auth.UserResponse

internal fun UserResponse.toUserType() = if (isAlreadyMember) UserType.EXISTING_USER else UserType.NEW_USER