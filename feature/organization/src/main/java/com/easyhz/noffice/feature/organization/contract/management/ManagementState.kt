package com.easyhz.noffice.feature.organization.contract.management

import com.easyhz.noffice.core.common.base.UiState
import com.easyhz.noffice.core.model.organization.OrganizationInformation
import com.easyhz.noffice.core.model.organization.category.Category
import com.easyhz.noffice.core.model.organization.member.MemberType

data class ManagementState(
    val isSaveLoading: Boolean,
    val isLoading: Boolean,
    val organizationInformation: OrganizationInformation,
    val selectedImage: String?,
    val isShowImageBottomSheet: Boolean,
    val categoryList: List<Category>
): UiState() {
    companion object {
        fun init() = ManagementState(
            isLoading = true,
            organizationInformation = OrganizationInformation(
                id = -1,
                name = "",
                profileImageUrl = "",
                category = emptyList(),
                members = linkedMapOf(MemberType.LEADER to 0, MemberType.PARTICIPANT to 0),
                hasStandbyMember = false,
                role = MemberType.PARTICIPANT
            ),
            selectedImage = "",
            isShowImageBottomSheet = false,
            isSaveLoading = true,
            categoryList = emptyList()
        )
        fun ManagementState.updateCategoryItem(selectedIndex: Int): ManagementState {
            val updatedCategory = categoryList.mapIndexed { index, categoryState ->
                categoryState.copy(isSelected = categoryState.isSelected.xor(index == selectedIndex))
            }
            return copy(categoryList = updatedCategory)
        }
    }
}
