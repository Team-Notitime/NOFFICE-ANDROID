package com.easyhz.noffice.feature.organization.contract.invitation

import com.easyhz.noffice.core.common.base.UiIntent

sealed class InvitationIntent: UiIntent() {
    data class InitScreen(val invitationUrl: String, val imageUrl: String): InvitationIntent()
    data object ClickHomeButton: InvitationIntent()
    data object ClickCopyUrl: InvitationIntent()
}