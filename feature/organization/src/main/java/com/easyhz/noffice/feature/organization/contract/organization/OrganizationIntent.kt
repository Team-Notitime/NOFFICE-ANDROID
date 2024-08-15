package com.easyhz.noffice.feature.organization.contract.organization

import com.easyhz.noffice.core.common.base.UiIntent

sealed class OrganizationIntent: UiIntent() {
    data object ClickOrganizationCreation: OrganizationIntent()
    data class ClickOrganization(val index: Int): OrganizationIntent()
}