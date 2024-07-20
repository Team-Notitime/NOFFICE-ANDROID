package com.easyhz.noffice.feature.organization.contract.invitation

import com.easyhz.noffice.core.common.base.UiSideEffect

sealed class InvitationSideEffect: UiSideEffect() {
    data object NavigateToHome: InvitationSideEffect()
    data class CopyUrl(val url: String): InvitationSideEffect()
}