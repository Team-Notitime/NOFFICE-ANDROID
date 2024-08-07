package com.easyhz.noffice.feature.organization.contract.member

import com.easyhz.noffice.core.common.base.UiState
import com.easyhz.noffice.feature.organization.util.member.MemberViewType

data class MemberState(
    val memberList: List<String>,
    val viewType: MemberViewType,
): UiState() {
    companion object {
        fun init() = MemberState(
            memberList = emptyList(),
            viewType = MemberViewType.MANAGEMENT
        )
    }
}