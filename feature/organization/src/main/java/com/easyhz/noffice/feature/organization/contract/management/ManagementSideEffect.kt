package com.easyhz.noffice.feature.organization.contract.management

import android.net.Uri
import com.easyhz.noffice.core.common.base.UiSideEffect

sealed class ManagementSideEffect: UiSideEffect() {
    data object NavigateToUp: ManagementSideEffect()
    data class NavigateToMemberManagement(val id: Int): ManagementSideEffect()
    data object NavigateToGallery: ManagementSideEffect()
    data class NavigateToCamera(val uri: Uri): ManagementSideEffect()
}