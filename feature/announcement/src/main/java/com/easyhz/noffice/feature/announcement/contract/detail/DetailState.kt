package com.easyhz.noffice.feature.announcement.contract.detail

import com.easyhz.noffice.core.common.base.UiState
import com.easyhz.noffice.core.model.announcement.Announcement
import com.easyhz.noffice.core.model.common.Member
import com.easyhz.noffice.core.model.organization.OrganizationInformation
import com.easyhz.noffice.core.model.organization.member.MemberType
import com.easyhz.noffice.core.model.task.Task
import com.easyhz.noffice.feature.announcement.util.detail.ReaderType

data class DetailState(
    val isLoading: Boolean,
    val isShowBottomSheet: Boolean,
    val isWebViewLoading: Boolean,
    val canGoBack: Boolean,
    val announcement: Announcement,
    val organizationInformation: OrganizationInformation,
    val taskList: List<Task>,
    val selectedReaderType: ReaderType,
    val readerList: List<Member>,
    val nonReaderList: List<Member>
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
                members = linkedMapOf(MemberType.LEADER to 0, MemberType.PARTICIPANT to 0),
                hasStandbyMember = false,
                role = MemberType.PARTICIPANT
            ),
            taskList = emptyList(),
            selectedReaderType = ReaderType.READER,
            readerList = emptyList(),
            nonReaderList = emptyList()
        )

        fun DetailState.updateDetailTitle(
            title: String
        ):DetailState = this.copy(
            announcement = this.announcement.copy(title = title)
        )
    }
}

