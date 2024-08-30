package com.easyhz.noffice.feature.my_page.screen

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewModelScope
import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.core.common.error.handleError
import com.easyhz.noffice.core.common.util.errorLogging
import com.easyhz.noffice.domain.my_page.usecase.LogoutUseCase
import com.easyhz.noffice.feature.my_page.contract.menu.MenuIntent
import com.easyhz.noffice.feature.my_page.contract.menu.MenuSideEffect
import com.easyhz.noffice.feature.my_page.contract.menu.MenuState
import com.easyhz.noffice.feature.my_page.util.MyPageMenu
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageMenuViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val logoutUseCase: LogoutUseCase
): BaseViewModel<MenuState, MenuIntent, MenuSideEffect>(
    initialState = MenuState.init()
) {
    override fun handleIntent(intent: MenuIntent) {
        when(intent) {
            is MenuIntent.ClickMenuItem -> { onClickMenuItem(intent.item) }
            is MenuIntent.ClickLogoutButton -> { onClickLogoutButton(intent.context, intent.isPositive) }
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
            MyPageMenu.LOGOUT -> {
                handleLogoutMenu()
            }
            else -> { }
        }
    }

    private fun navigateToInquiry() {
        val uri = Uri.parse("http://pf.kakao.com/_IsRgG")
        postSideEffect { MenuSideEffect.NavigateToInquiry(uri) }
    }

    private fun handleNotificationMenu() {
        navigateNotificationSetting()
//        reduce { copy(isCheckedNotification = !currentState.isCheckedNotification) }
    }

    private fun handleNoticeMenu() {
        val uri = Uri.parse("https://gkftndltek.notion.site/Noffice-accc5b9fc84941de9625e45da03d4ab8?pvs=4")
        postSideEffect { MenuSideEffect.NavigateToNotice(uri) }
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

    private fun handleLogoutMenu() {
        reduce { copy(isShowLogoutDialog = true) }
    }

    private fun onClickLogoutButton(context: Context, isPositive: Boolean) = viewModelScope.launch {
        if (isPositive) {
            handleLogout(context)
        } else {
            updateLogoutState(isLoading = false)
        }
    }

    private suspend fun handleLogout(context: Context) {
        updateLogoutState(isLoading = true)
        logoutUseCase.invoke(context)
            .onSuccess {
                postSideEffect { MenuSideEffect.NavigateToLogin }
            }.onFailure {
                errorLogging(this.javaClass.name, "handleLogout", it)
                showSnackBar(it.handleError())
            }.also {
                reduce { copy(isLoading = false) }
            }
    }

    private fun updateLogoutState(isShowLogoutDialog: Boolean = false, isLoading: Boolean) {
        reduce { copy(isShowLogoutDialog = isShowLogoutDialog, isLoading = isLoading) }
    }

    private fun showSnackBar(stringId: Int) {
        postSideEffect { MenuSideEffect.ShowSnackBar(stringId) }
    }

    private fun navigateNotificationSetting() {
        val intent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationSettingOreo(context)
        } else {
            notificationSettingOreoLess(context)
        }
        try {
            context.startActivity(intent)
        }catch (e: ActivityNotFoundException) {
            e.printStackTrace()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun notificationSettingOreo(context: Context): Intent {
        return Intent().also { intent ->
            intent.action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
    }

    private fun notificationSettingOreoLess(context: Context): Intent {
        return Intent().also { intent ->
            intent.action = "android.settings.APP_NOTIFICATION_SETTINGS"
            intent.putExtra("app_package", context.packageName)
            intent.putExtra("app_uid", context.applicationInfo?.uid)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
    }
}