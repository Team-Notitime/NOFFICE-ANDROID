package com.easyhz.noffice.feature.home.screen.splash

import androidx.lifecycle.viewModelScope
import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.core.common.manager.DeepLinkManager
import com.easyhz.noffice.core.model.splash.EnterScreenType
import com.easyhz.noffice.domain.home.usecase.splash.GetSplashInfoUseCase
import com.easyhz.noffice.domain.organization.usecase.organization.FetchOrganizationSignUpInfoUseCase
import com.easyhz.noffice.feature.home.contract.splash.SplashIntent
import com.easyhz.noffice.feature.home.contract.splash.SplashSideEffect
import com.easyhz.noffice.feature.home.contract.splash.SplashState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getSplashInfoUseCase: GetSplashInfoUseCase,
    private val fetchOrganizationSignUpInfoUseCase: FetchOrganizationSignUpInfoUseCase
) : BaseViewModel<SplashState, SplashIntent, SplashSideEffect>(
    initialState = SplashState
) {
    override fun handleIntent(intent: SplashIntent) {
        when (intent) {
            is SplashIntent.InitScreen -> {
                initScreen(intent.announcementId, intent.organizationId)
            }
        }
    }

    private fun initScreen(announcementId: Int, organizationId: Int) = viewModelScope.launch {
        val enterScreenType = getSplashInfoUseCase.invoke(Unit).getOrNull()?.takeIf {
            it == EnterScreenType.HOME || organizationId == -1
        } ?: EnterScreenType.ONBOARDING

        handleNavigation(enterScreenType, announcementId, organizationId)
    }

    private suspend fun handleNavigation(enterScreenType: EnterScreenType, announcementId: Int, organizationId: Int) {
        delay(300)

        when (enterScreenType) {
            EnterScreenType.HOME -> handleHomeNavigation(announcementId, organizationId) /* 로그인 한 상태 */
            else -> { /* 로그인 안 한 상태 */
                if (organizationId != -1) {
                    DeepLinkManager.setOrganizationIdToJoin(organizationId)
                }
                navigateBasedOnEnterScreenType(enterScreenType)
            }
        }
    }

    /* 로그인 한 상태 */
    private fun handleHomeNavigation(announcementId: Int, organizationId: Int) {
        when {
            announcementId != -1 && organizationId != -1 -> navigateToAnnouncementDetail(announcementId, organizationId)
            organizationId != -1 -> navigateToOrganizationJoin(organizationId)
            else -> navigateBasedOnEnterScreenType(EnterScreenType.HOME)
        }
    }

    /* 로그인 안 한 상태 */
    private fun navigateBasedOnEnterScreenType(enterScreenType: EnterScreenType) {
        val sideEffect = when (enterScreenType) {
            EnterScreenType.ONBOARDING -> SplashSideEffect.NavigateToOnboarding
            EnterScreenType.LOGIN -> SplashSideEffect.NavigateToLogin
            EnterScreenType.HOME -> SplashSideEffect.NavigateToHome
        }
        postSideEffect { sideEffect }
    }

    private fun navigateToAnnouncementDetail(announcementId: Int, organizationId: Int) {
        postSideEffect { SplashSideEffect.NavigateToAnnouncementDetail(announcementId, organizationId) }
    }

    private fun navigateToOrganizationJoin(organizationId: Int) = viewModelScope.launch {
        val organizationSignUpInformation = fetchOrganizationSignUpInfoUseCase.invoke(organizationId).getOrNull()
        if (organizationSignUpInformation != null) {
            postSideEffect { SplashSideEffect.NavigateToOrganizationJoin(organizationSignUpInformation) }
        } else {
            navigateBasedOnEnterScreenType(EnterScreenType.HOME)
        }
    }

}