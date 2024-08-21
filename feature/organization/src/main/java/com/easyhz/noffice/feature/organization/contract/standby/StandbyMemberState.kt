package com.easyhz.noffice.feature.organization.contract.standby

import com.easyhz.noffice.core.common.base.UiState
import com.easyhz.noffice.core.model.common.Member

data class StandbyMemberState(
    val isLoading: Boolean,
    val organizationId: Int,
    val memberList: List<Member>,
) : UiState() {
    companion object {
        fun init() = StandbyMemberState(
            isLoading = false,
            organizationId = -1,
            memberList = emptyList()
        )

        fun StandbyMemberState.toggleMember(index: Int): StandbyMemberState =
            copy(memberList = memberList.mapIndexed { i, item ->
                if (i == index) item.copy(isSelected = !item.isSelected) else item
            })
    }

    fun StandbyMemberState.toggleAllMembers(): StandbyMemberState =
        copy(
            memberList = memberList.map { it.copy(isSelected = true) },
        )

}
