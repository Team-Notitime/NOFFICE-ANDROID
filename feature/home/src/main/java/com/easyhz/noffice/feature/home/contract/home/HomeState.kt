package com.easyhz.noffice.feature.home.contract.home

import com.easyhz.noffice.core.common.base.UiState
import com.easyhz.noffice.core.model.auth.UserInfo
import com.easyhz.noffice.feature.home.util.HomeTopBarMenu

data class HomeState(
    val topBarMenu: HomeTopBarMenu,
    val userInfo: UserInfo,
    val name: String,
    val date: String,
    val isJoinLoading: Boolean,
    val isInitLoading: Boolean,
    val isTaskLoading: Boolean,
): UiState() {
    companion object {
        fun init() = HomeState(
            topBarMenu = HomeTopBarMenu.NOTICE,
            userInfo = UserInfo(
                alias = "",
                id = -1,
                name = "",
                organizations = emptyList(),
                profileImage = ""
            ),
            name = "",
            date = "",
            isJoinLoading = false,
            isInitLoading = true,
            isTaskLoading = false
        )
    }
}