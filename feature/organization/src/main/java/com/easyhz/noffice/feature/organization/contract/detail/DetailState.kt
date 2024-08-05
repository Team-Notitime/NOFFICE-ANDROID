package com.easyhz.noffice.feature.organization.contract.detail

import androidx.annotation.StringRes
import com.easyhz.noffice.core.common.base.UiState
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.model.announcement.detail.AnnouncementDetail
import com.easyhz.noffice.core.model.organization.OrganizationInformation

data class DetailState(
    val isLoading: Boolean,
    val isCardLoading: Boolean,
    val organizationInformation: OrganizationInformation,
    val numberOfMembers: LinkedHashMap<MemberType, Int>,
    val hasWaitingMember: Boolean,
    val announcementList: List<AnnouncementDetail>
): UiState() {
    companion object {
        fun init() = DetailState(
            isLoading = true,
            isCardLoading = true,
            organizationInformation = OrganizationInformation(
                name = "",
                profileImageUrl = "",
                category = emptyList(),
            ),
            numberOfMembers = linkedMapOf(MemberType.LEADER to 0, MemberType.MEMBER to 0),
            hasWaitingMember = false,
            announcementList = emptyList()
        )

        fun DetailState.updateOrganizationName(
            name: String
        ): DetailState = this.copy(
            organizationInformation = this.organizationInformation.copy(name = name)
        )
    }
}

enum class MemberType(
    @StringRes val stringId: Int
) {
    LEADER(
        stringId = R.string.organization_member_type_leader
    ), MEMBER(
        stringId = R.string.organization_member_type_member
    )
}
