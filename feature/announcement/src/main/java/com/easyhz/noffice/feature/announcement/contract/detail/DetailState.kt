package com.easyhz.noffice.feature.announcement.contract.detail

import com.easyhz.noffice.core.common.base.UiState
import com.easyhz.noffice.core.model.announcement.Announcement
import com.easyhz.noffice.core.model.announcement.detail.AnnouncementDetail
import com.easyhz.noffice.core.model.organization.OrganizationInformation
import com.easyhz.noffice.core.model.organization.member.MemberType
import com.easyhz.noffice.core.model.task.Task

data class DetailState(
    val isLoading: Boolean,
    val isShowBottomSheet: Boolean,
    val isWebViewLoading: Boolean,
    val canGoBack: Boolean,
    val announcement: Announcement,
    val organizationInformation: OrganizationInformation,
    val taskList: List<Task>
): UiState() {
    companion object {
        fun init() = DetailState(
            isLoading = true,
            isShowBottomSheet = false,
            isWebViewLoading = true,
            canGoBack = false,
            announcement = Announcement(
                announcementId = -1,
                content = "",
                createdAt = "",
                endAt = "",
                memberId = -1,
                noticeAt = emptyList(),
                organizationId = -1,
                placeLinkUrl = null,
                placeLinkName = null,
                profileImageUrl = null,
                title = "",
                updatedAt = ""
            ),
            organizationInformation = OrganizationInformation(
                id = -1,
                name = "",
                profileImageUrl = "",
                category = emptyList(),
                members = linkedMapOf(MemberType.LEADER to 0, MemberType.MEMBER to 0),
                hasStandbyMember = false,
                role = MemberType.MEMBER
            ),
            taskList = emptyList()
        )

        fun DetailState.updateDetailTitle(
            title: String
        ):DetailState = this.copy(
            announcement = this.announcement.copy(title = title)
        )
    }
}

