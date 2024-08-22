package com.easyhz.noffice.feature.home.contract.home

import com.easyhz.noffice.core.common.base.UiIntent
import com.easyhz.noffice.core.design_system.util.topBar.TopBarIconMenu
import com.easyhz.noffice.feature.home.util.HomeTopBarMenu

sealed class HomeIntent: UiIntent() {
    data class ChangeTopBarMenu(val topBarMenu: HomeTopBarMenu): HomeIntent()
    data class ClickTopBarIconMenu(val iconMenu: TopBarIconMenu): HomeIntent()
    data class JoinToOrganization(val organizationId: Int): HomeIntent()
    data object SetInitLoading: HomeIntent()
    data object Refresh: HomeIntent()
}