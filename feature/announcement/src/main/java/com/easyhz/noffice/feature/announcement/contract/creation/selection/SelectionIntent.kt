package com.easyhz.noffice.feature.announcement.contract.creation.selection

import com.easyhz.noffice.core.common.base.UiIntent

sealed class SelectionIntent: UiIntent() {
    data object ClickBackButton: SelectionIntent()
    data object ClickNextButton: SelectionIntent()
    data object ClickPositiveButton: SelectionIntent()
    data object ShowOrganizationCreationDialog: SelectionIntent()
    data object HideOrganizationCreationDialog: SelectionIntent()
    data object ClickOrganizationCreation: SelectionIntent()
    data class SelectedOrganization(val id: Int): SelectionIntent()
}