package com.easyhz.noffice.feature.organization.contract.member

import com.easyhz.noffice.core.common.base.UiState
import com.easyhz.noffice.core.model.organization.member.MemberType
import com.easyhz.noffice.feature.organization.util.member.MemberViewType

data class MemberState(
    val memberList: List<String>,
    val viewType: MemberViewType,
    val isOpenBottomSheet: Boolean,
    val authorityType: MemberType,
): UiState() {
    companion object {
        fun init() = MemberState(
            memberList = emptyList(),
            viewType = MemberViewType.MANAGEMENT,
            isOpenBottomSheet = false,
            authorityType = MemberType.LEADER
        )
    }
}