package com.easyhz.noffice.feature.organization.contract.member

import com.easyhz.noffice.core.common.base.UiState
import com.easyhz.noffice.core.model.common.Member
import com.easyhz.noffice.core.model.organization.member.MemberType
import com.easyhz.noffice.feature.organization.util.member.MemberViewType

data class MemberState(
    val organizationId: Int,
    val imageUrl: String?,
    val memberList: List<Member>,
    val viewType: MemberViewType,
    val isOpenBottomSheet: Boolean,
    val authorityType: MemberType,
    val isLoading: Boolean
): UiState() {
    companion object {
        fun init() = MemberState(
            organizationId = -1,
            imageUrl = null,
            memberList = emptyList(),
            viewType = MemberViewType.MANAGEMENT,
            isOpenBottomSheet = false,
            authorityType = MemberType.LEADER,
            isLoading = true
        )
    }
}