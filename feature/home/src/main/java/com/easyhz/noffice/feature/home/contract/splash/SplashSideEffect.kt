package com.easyhz.noffice.feature.home.contract.splash

import com.easyhz.noffice.core.common.base.UiSideEffect
import com.easyhz.noffice.core.model.organization.OrganizationSignUpInformation

sealed class SplashSideEffect: UiSideEffect() {
    data object NavigateToHome: SplashSideEffect()
    data object NavigateToOnboarding: SplashSideEffect()
    data object NavigateToLogin: SplashSideEffect()
    data class NavigateToAnnouncementDetail(val announcementId: Int, val organizationId: Int): SplashSideEffect()
    data class NavigateToOrganizationJoin(val organizationSignUpInformation: OrganizationSignUpInformation): SplashSideEffect()
    // FIXME 약관 창 가야 하는지 질문
}