package com.easyhz.noffice.core.model.organization.member

import androidx.annotation.StringRes
import com.easyhz.noffice.core.model.R

enum class MemberType(
    @StringRes val stringId: Int
) {
    LEADER(
        stringId = R.string.organization_member_type_leader
    ), MEMBER(
        stringId = R.string.organization_member_type_member
    )
}