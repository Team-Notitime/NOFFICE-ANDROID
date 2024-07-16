package com.easyhz.noffice.feature.home.contract.home

import com.easyhz.noffice.core.common.base.UiIntent
import com.easyhz.noffice.feature.home.util.HomeTopBarMenu

sealed class HomeIntent: UiIntent() {
    data class ChangeTopBarMenu(val topBarMenu: HomeTopBarMenu): HomeIntent()
}