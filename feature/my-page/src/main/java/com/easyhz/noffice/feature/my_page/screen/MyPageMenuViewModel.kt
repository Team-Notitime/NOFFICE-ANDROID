package com.easyhz.noffice.feature.my_page.screen

import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.feature.my_page.contract.menu.MenuIntent
import com.easyhz.noffice.feature.my_page.contract.menu.MenuSideEffect
import com.easyhz.noffice.feature.my_page.contract.menu.MenuState
import com.easyhz.noffice.feature.my_page.util.MyPageMenu
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyPageMenuViewModel @Inject constructor(

): BaseViewModel<MenuState, MenuIntent, MenuSideEffect>(
    initialState = MenuState.init()
) {
    override fun handleIntent(intent: MenuIntent) {
        when(intent) {
            is MenuIntent.ClickMenuItem -> { onClickMenuItem(intent.item) }
            is MenuIntent.ClickSignOutButton -> { onClickSignOutButton(intent.isPositive) }
        }

    }

    private fun onClickMenuItem(item: MyPageMenu) {
        when(item) {
            MyPageMenu.NOTIFICATION -> {
                handleNotificationMenu()
            }
            MyPageMenu.NOTICE -> {
                handleNoticeMenu()
            }
            MyPageMenu.SIGN_OUT -> {
                handleSignOutMenu()
            }
            else -> {

            }
        }
    }

    private fun handleNotificationMenu() {
        reduce { copy(isCheckedNotification = !currentState.isCheckedNotification) }
    }

    private fun handleNoticeMenu() {
//        postSideEffect { MyPageSideEffect.NavigateToNotice }
    }

    private fun handleSignOutMenu() {
        reduce { copy(isShowSignOutDialog = true) }
    }

    private fun onClickSignOutButton(isPositive: Boolean) {
        if (isPositive) {
            //로그아웃 로직
        } else {
            reduce { copy(isShowSignOutDialog = false) }
        }
    }
}