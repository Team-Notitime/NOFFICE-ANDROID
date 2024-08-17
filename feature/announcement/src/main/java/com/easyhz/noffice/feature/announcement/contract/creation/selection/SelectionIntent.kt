package com.easyhz.noffice.feature.announcement.contract.creation.selection

import com.easyhz.noffice.core.common.base.UiIntent

sealed class SelectionIntent: UiIntent() {
    data object ClickBackButton: SelectionIntent()
    data object ClickNextButton: SelectionIntent()
    data class SelectedOrganization(val id: Int): SelectionIntent()
}