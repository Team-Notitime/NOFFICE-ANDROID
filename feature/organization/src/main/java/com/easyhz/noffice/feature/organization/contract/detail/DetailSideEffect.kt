package com.easyhz.noffice.feature.organization.contract.detail

import com.easyhz.noffice.core.common.base.UiSideEffect
import com.easyhz.noffice.core.model.organization.OrganizationInformation

sealed class DetailSideEffect: UiSideEffect() {
    data object NavigateToUp: DetailSideEffect()
    data class NavigateToAnnouncementDetail(val id: Int, val title: String): DetailSideEffect()
    data class NavigateToOrganizationManagement(val information: OrganizationInformation, val numberOfMembers: LinkedHashMap<MemberType, Int>): DetailSideEffect()
}