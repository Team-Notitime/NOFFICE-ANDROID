package com.easyhz.noffice.feature.organization.contract.member

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.easyhz.noffice.core.common.base.UiState
import com.easyhz.noffice.core.model.common.Member
import com.easyhz.noffice.core.model.organization.member.MemberType
import com.easyhz.noffice.feature.organization.util.member.MemberViewType

data class MemberState(
    val organizationId: Int,
    val imageUrl: String?,
    val memberList: SnapshotStateList<Member>,
    val viewType: MemberViewType,
    val isOpenBottomSheet: Boolean,
    val authorityType: MemberType,
    val isLoading: Boolean,
    val isShowDialog: Boolean,
): UiState() {
    companion object {
        fun init() = MemberState(
            organizationId = -1,
            imageUrl = null,
            memberList = mutableStateListOf(),
            viewType = MemberViewType.MANAGEMENT,
            isOpenBottomSheet = false,
            authorityType = MemberType.LEADER,
            isLoading = true,
            isShowDialog = false
        )
    }
}