package com.easyhz.noffice.feature.organization.contract.detail

import com.easyhz.noffice.core.common.base.UiIntent

sealed class DetailIntent: UiIntent() {
    data class InitScreen(val organizationId: Int, val organizationName: String): DetailIntent()
    data object NavigateToUp: DetailIntent()
}