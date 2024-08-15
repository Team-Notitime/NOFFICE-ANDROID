package com.easyhz.noffice.feature.my_page.contract.menu

import com.easyhz.noffice.core.common.base.UiIntent
import com.easyhz.noffice.feature.my_page.util.MyPageMenu

sealed class MenuIntent: UiIntent() {
    data class ClickMenuItem(val item: MyPageMenu): MenuIntent()
    data class ClickSignOutButton(val isPositive: Boolean): MenuIntent()
}