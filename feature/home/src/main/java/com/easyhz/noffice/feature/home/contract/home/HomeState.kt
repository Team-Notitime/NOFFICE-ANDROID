package com.easyhz.noffice.feature.home.contract.home

import com.easyhz.noffice.core.common.base.UiState
import com.easyhz.noffice.feature.home.util.HomeTopBarMenu

data class HomeState(
    val topBarMenu: HomeTopBarMenu,
): UiState() {
    companion object {
        fun init() = HomeState(
            topBarMenu = HomeTopBarMenu.NOTICE
        )
    }
}