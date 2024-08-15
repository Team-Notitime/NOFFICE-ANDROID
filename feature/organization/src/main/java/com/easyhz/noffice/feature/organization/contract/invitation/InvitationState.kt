package com.easyhz.noffice.feature.organization.contract.invitation

import com.easyhz.noffice.core.common.base.UiState

data class InvitationState(
    val invitationUrl: String,
    val imageUrl: String,
): UiState() {
    companion object {
        fun init() = InvitationState(
            invitationUrl = "",
            imageUrl = ""
        )
    }
}
