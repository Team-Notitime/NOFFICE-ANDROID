package com.easyhz.noffice.feature.organization.contract.organization

import com.easyhz.noffice.core.common.base.UiIntent
import com.easyhz.noffice.core.design_system.util.topBar.TopBarIconMenu

sealed class OrganizationIntent: UiIntent() {
    data object ClickOrganizationCreation: OrganizationIntent()
    data class ClickTopBarIconMenu(val iconMenu: TopBarIconMenu): OrganizationIntent()
    data class ClickOrganization(val id: Int, val name: String): OrganizationIntent()
}