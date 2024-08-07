package com.easyhz.noffice.core.model.organization.member

import androidx.annotation.StringRes
import com.easyhz.noffice.core.model.R

enum class MemberType(
    @StringRes val stringId: Int,
    @StringRes val subStringId: Int,
) {
    LEADER(
        stringId = R.string.organization_member_type_leader,
        subStringId = R.string.organization_member_type_leader_sub
    ), MEMBER(
        stringId = R.string.organization_member_type_member,
        subStringId = R.string.organization_member_type_member_sub,
    )
}