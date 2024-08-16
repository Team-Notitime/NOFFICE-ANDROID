package com.easyhz.noffice.feature.organization.contract.creation

import android.net.Uri
import androidx.annotation.StringRes
import com.easyhz.noffice.core.common.base.UiSideEffect

sealed class CreationSideEffect: UiSideEffect() {
    data object ClearFocus: CreationSideEffect()
    data object NavigateToGallery: CreationSideEffect()
    data class NavigateToCamera(val uri: Uri): CreationSideEffect()
    data class NavigateToInvitation(val invitationUrl: String, val imageUrl: String?): CreationSideEffect()
    data object NavigateToUp: CreationSideEffect()
    data class ShowSnackBar(@StringRes val stringId: Int): CreationSideEffect()
}