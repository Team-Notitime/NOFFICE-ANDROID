package com.easyhz.noffice.feature.organization.contract.detail

import com.easyhz.noffice.core.common.base.UiSideEffect

sealed class DetailSideEffect: UiSideEffect() {
    data object NavigateToUp: DetailSideEffect()
    data class NavigateToAnnouncementDetail(val id: Int, val title: String): DetailSideEffect()
}