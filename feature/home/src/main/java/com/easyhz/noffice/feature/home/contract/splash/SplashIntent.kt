package com.easyhz.noffice.feature.home.contract.splash

import com.easyhz.noffice.core.common.base.UiIntent

sealed class SplashIntent: UiIntent() {
    data class InitScreen(val announcementId: Int, val organizationId: Int): SplashIntent()
}