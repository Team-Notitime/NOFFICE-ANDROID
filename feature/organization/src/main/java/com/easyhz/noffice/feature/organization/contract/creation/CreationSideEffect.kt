package com.easyhz.noffice.feature.organization.contract.creation

import com.easyhz.noffice.core.common.base.UiSideEffect

sealed class CreationSideEffect: UiSideEffect() {
    data object ClearFocus: CreationSideEffect()
    data object NavigateToGallery: CreationSideEffect()
    data object NavigateToCamera: CreationSideEffect()
    data class NavigateToInvitation(val invitationUrl: String, val imageUrl: String): CreationSideEffect()
    data object NavigateToUp: CreationSideEffect()
}