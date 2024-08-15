package com.easyhz.noffice.feature.organization.contract.management

import com.easyhz.noffice.core.common.base.UiState
import com.easyhz.noffice.core.model.organization.OrganizationInformation
import com.easyhz.noffice.core.model.organization.category.CATEGORY
import com.easyhz.noffice.core.model.organization.category.Category
import com.easyhz.noffice.core.model.organization.category.toState
import com.easyhz.noffice.core.model.organization.member.MemberType

data class ManagementState(
    val isLoading: Boolean,
    val organizationInformation: OrganizationInformation,
    val selectedImage: String,
    val category: List<Category>,
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
            category = CATEGORY.toState(),
            selectedImage = "",
            isShowImageBottomSheet = false
        )
        fun ManagementState.updateCategoryItem(selectedIndex: Int): ManagementState {
            val updatedCategory = category.mapIndexed { index, categoryState ->
                categoryState.copy(isSelected = categoryState.isSelected.xor(index == selectedIndex))
            }
            return copy(category = updatedCategory)
        }
    }
}
