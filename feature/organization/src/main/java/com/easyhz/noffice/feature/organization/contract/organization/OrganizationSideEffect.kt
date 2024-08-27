package com.easyhz.noffice.feature.organization.contract.organization

import com.easyhz.noffice.core.common.base.UiSideEffect

sealed class OrganizationSideEffect: UiSideEffect() {
    data object NavigateToCreation: OrganizationSideEffect()
    data class NavigateToDetail(val id: Int, val name: String): OrganizationSideEffect()
    data object NavigateToMyPage: OrganizationSideEffect()
    data object Refresh: OrganizationSideEffect()
}