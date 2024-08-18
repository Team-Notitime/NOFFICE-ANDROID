package com.easyhz.noffice.core.model.auth

import com.easyhz.noffice.core.model.organization.Organization

data class UserInfo(
    val alias: String,
    val id: Int,
    val name: String,
    val organizations: List<Organization>,
    val profileImage:String?
)
