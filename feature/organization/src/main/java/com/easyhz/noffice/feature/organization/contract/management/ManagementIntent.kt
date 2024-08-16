package com.easyhz.noffice.feature.organization.contract.management

import android.net.Uri
import com.easyhz.noffice.core.common.base.UiIntent
import com.easyhz.noffice.core.design_system.util.bottomSheet.ImageSelectionBottomSheetItem
import com.easyhz.noffice.core.model.organization.OrganizationInformation

sealed class ManagementIntent : UiIntent() {
    data class InitScreen(val organizationInformation: OrganizationInformation, ) : ManagementIntent()
    data object NavigateToUp: ManagementIntent()
    data class ClickCategoryItem(val index: Int) : ManagementIntent()
    data object ClickProfileImage: ManagementIntent()
    data class ClickImageBottomSheetItem(val bottomSheetItem: ImageSelectionBottomSheetItem) : ManagementIntent()
    data object HideImageBottomSheet: ManagementIntent()
    data class PickImage(val uri: Uri?) : ManagementIntent()
    data class TakePicture(val isUsed: Boolean) : ManagementIntent()
    data object ClickMemberManagementButton: ManagementIntent()
    data object ClickSaveButton: ManagementIntent()
}