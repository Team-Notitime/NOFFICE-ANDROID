package com.easyhz.noffice.feature.organization.contract.management

import com.easyhz.noffice.core.common.base.UiState
import com.easyhz.noffice.core.model.organization.OrganizationInformation
import com.easyhz.noffice.core.model.organization.member.MemberType

data class ManagementState(
    val isSaveLoading: Boolean,
    val isLoading: Boolean,
    val organizationInformation: OrganizationInformation,
    val selectedImage: String,
    val isShowImageBottomSheet: Boolean
): UiState() {
    companion object {
        fun init() = ManagementState(
            isLoading = true,
            organizationInformation = OrganizationInformation(
                id = -1,
                name = "",
                profileImageUrl = "",
                category = emptyList(),
                members = linkedMapOf(MemberType.LEADER to 0, MemberType.MEMBER to 0),
                hasStandbyMember = false
            ),
            selectedImage = "",
            isShowImageBottomSheet = false,
            isSaveLoading = false
        )
        fun ManagementState.updateCategoryItem(selectedIndex: Int): ManagementState {
            val updatedCategory = organizationInformation.category.mapIndexed { index, categoryState ->
                categoryState.copy(isSelected = categoryState.isSelected.xor(index == selectedIndex))
            }
            return copy(organizationInformation = organizationInformation.copy(category = updatedCategory),)
        }
    }
}
