package com.easyhz.noffice.feature.my_page.screen

import android.net.Uri
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
            MyPageMenu.INQUIRY -> { navigateToInquiry() }
            MyPageMenu.NOTICE -> { handleNoticeMenu() }
            MyPageMenu.TERMS_OF_SERVICE -> { handleTermsOfService() }
            MyPageMenu.PRIVACY_POLICY -> { handlePrivacyPolicy() }
            MyPageMenu.NOTIFICATION -> { handleNotificationMenu() }
            MyPageMenu.CONSENT_TO_INFORMATION -> { handleConsentToInformation() }
            MyPageMenu.WITHDRAWAL -> { handleWithdrawal() }
            MyPageMenu.SIGN_OUT -> {
                handleSignOutMenu()
            }
            else -> { }
        }
    }

    private fun navigateToInquiry() {
        val uri = Uri.parse("http://pf.kakao.com/_IsRgG")
        postSideEffect { MenuSideEffect.NavigateToInquiry(uri) }
    }

    private fun handleNotificationMenu() {
        reduce { copy(isCheckedNotification = !currentState.isCheckedNotification) }
    }

    private fun handleNoticeMenu() {
        postSideEffect { MenuSideEffect.NavigateToNotice }
    }

    private fun handleTermsOfService() {
        postSideEffect { MenuSideEffect.NavigateToServiceOfTerms }
    }

    private fun handlePrivacyPolicy() {
        postSideEffect { MenuSideEffect.NavigateToPrivacyPolicy }
    }

    private fun handleConsentToInformation() {
        postSideEffect { MenuSideEffect.NavigateToConsentToInformation }
    }

    private fun handleWithdrawal() {
        postSideEffect { MenuSideEffect.NavigateToWithdrawal }
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