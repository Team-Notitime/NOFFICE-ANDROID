package com.easyhz.noffice.feature.organization.contract.management

import android.net.Uri
import com.easyhz.noffice.core.common.base.UiIntent
import com.easyhz.noffice.core.model.organization.OrganizationInformation
import com.easyhz.noffice.core.model.organization.member.MemberType
import com.easyhz.noffice.feature.organization.util.creation.BottomSheetItem

sealed class ManagementIntent : UiIntent() {
    data class InitScreen(
        val organizationInformation: OrganizationInformation,
        val numberOfMembers: LinkedHashMap<MemberType, Int>,
    ) : ManagementIntent()

    data object NavigateToUp: ManagementIntent()
    data class ClickCategoryItem(val index: Int) : ManagementIntent()
    data object ClickProfileImage: ManagementIntent()
    data class ClickImageBottomSheetItem(val bottomSheetItem: BottomSheetItem) : ManagementIntent()
    data object HideImageBottomSheet: ManagementIntent()
    data class PickImage(val uri: Uri?) : ManagementIntent()
    data class TakePicture(val isUsed: Boolean) : ManagementIntent()
    data object ClickMemberManagementButton: ManagementIntent()
}