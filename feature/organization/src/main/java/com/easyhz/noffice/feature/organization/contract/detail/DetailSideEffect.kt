package com.easyhz.noffice.feature.organization.contract.detail

import androidx.annotation.StringRes
import com.easyhz.noffice.core.common.base.UiSideEffect
import com.easyhz.noffice.core.model.organization.OrganizationInformation

sealed class DetailSideEffect: UiSideEffect() {
    data object NavigateToUp: DetailSideEffect()
    data class NavigateToAnnouncementDetail(val organizationId: Int, val id: Int, val title: String): DetailSideEffect()
    data class NavigateToStandbyMember(val id: Int): DetailSideEffect()
    data class NavigateToOrganizationManagement(val information: OrganizationInformation): DetailSideEffect()
    data class ShowSnackBar(@StringRes val stringId: Int): DetailSideEffect()
}