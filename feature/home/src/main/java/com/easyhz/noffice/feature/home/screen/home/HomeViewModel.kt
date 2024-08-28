package com.easyhz.noffice.feature.home.screen.home

import androidx.annotation.StringRes
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.core.common.error.HttpError
import com.easyhz.noffice.core.common.error.handleError
import com.easyhz.noffice.core.common.manager.DeepLinkManager
import com.easyhz.noffice.core.common.util.DateFormat
import com.easyhz.noffice.core.common.util.errorLogging
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.util.topBar.TopBarIconMenu
import com.easyhz.noffice.core.model.organization.Organization
import com.easyhz.noffice.domain.organization.usecase.organization.FetchOrganizationSignUpInfoUseCase
import com.easyhz.noffice.domain.organization.usecase.organization.FetchOrganizationsUseCase
import com.easyhz.noffice.feature.home.contract.home.HomeIntent
import com.easyhz.noffice.feature.home.contract.home.HomeSideEffect
import com.easyhz.noffice.feature.home.contract.home.HomeState
import com.easyhz.noffice.feature.home.util.HomeTopBarMenu
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchUserInfoUseCase: com.easyhz.noffice.domain.my_page.usecase.FetchUserInfoUseCase,
    private val fetchOrganizationsUseCase: FetchOrganizationsUseCase,
    private val fetchOrganizationSignUpInfoUseCase: FetchOrganizationSignUpInfoUseCase,
): BaseViewModel<HomeState, HomeIntent, HomeSideEffect>(
    initialState = HomeState.init()
) {
    private val _organizationState: MutableStateFlow<PagingData<Organization>> =
        MutableStateFlow(value = PagingData.empty())
    val organizationState: MutableStateFlow<PagingData<Organization>>
        get() = _organizationState

    override fun handleIntent(intent: HomeIntent) {
        when(intent) {
            is HomeIntent.ChangeTopBarMenu ->  { onChangeTopBarMenu(intent.topBarMenu) }
            is HomeIntent.ClickTopBarIconMenu -> { onClickTopBarIconMenu(intent.iconMenu) }
            is HomeIntent.JoinToOrganization -> { joinToOrganization(intent.organizationId) }
            is HomeIntent.Refresh -> { refresh() }
            is HomeIntent.SetInitLoading -> { reduce { copy(isInitLoading = false) }}
            is HomeIntent.ClickOrganizationHeader -> { navigateToOrganizationDetail(intent.organizationId, intent.organizationName) }
            is HomeIntent.ClickAnnouncementCard -> { navigateToAnnouncementDetail(intent.organizationId, intent.announcementId) }
        }
    }

    init {
        fetchUserInfo()
        getDateNow()
        fetchOrganizations()
    }

    private fun fetchUserInfo() = viewModelScope.launch {
        if (currentState.userInfo.id != -1) return@launch
        fetchUserInfoUseCase.invoke(Unit).onSuccess {
            reduce { copy(userInfo = it, name = it.alias) }
        }.onFailure {
            errorLogging(this.javaClass.name, "fetchUserInfo", it)
            showSnackBar(it.handleError())
            reduce { copy(isInitLoading = false)}
        }
    }

    private fun getDateNow() {
       val date = DateFormat.getDateNow()
        if (currentState.date == date) return
        reduce { copy(date = date) }
    }

    private fun onChangeTopBarMenu(topBarMenu: HomeTopBarMenu) {
        reduce { copy(topBarMenu = topBarMenu) }
    }

    private fun onClickTopBarIconMenu(iconMenu: TopBarIconMenu) {
        when(iconMenu) {
            TopBarIconMenu.NOTIFICATION -> { navigateToNotification() }
            TopBarIconMenu.USER -> { navigateToMyPageScreen() }
        }
    }

    private fun navigateToMyPageScreen() {
        postSideEffect { HomeSideEffect.NavigateToMyPage }
    }

    private fun navigateToNotification() {
        postSideEffect { HomeSideEffect.NavigateToNotification }
    }

    private fun navigateToOrganizationDetail(id: Int, name: String) {
        postSideEffect { HomeSideEffect.NavigateToOrganizationDetail(organizationId = id, organizationName = name) }
    }

    private fun navigateToAnnouncementDetail(organizationId: Int, id: Int) {
        postSideEffect { HomeSideEffect.NavigateToAnnouncementDetail(organizationId, id) }
    }


    private fun fetchOrganizations() = viewModelScope.launch {
        fetchOrganizationsUseCase.invoke()
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collectLatest { pagingData ->
                _organizationState.value = pagingData
            }
    }

    /* 조직 가입 */
    private fun joinToOrganization(id: Int) = viewModelScope.launch {
        if (id == -1) return@launch
        reduce { copy(isJoinLoading = true) }
        fetchOrganizationSignUpInfoUseCase.invoke(id).onSuccess {
            delay(500)
            postSideEffect { HomeSideEffect.NavigateToOrganizationJoin(it) }
        }.onFailure { error ->
            val messageResId = when (error) {
                is HttpError.ForbiddenError -> R.string.organization_join_already
                else -> error.handleError()
            }
            showSnackBar(messageResId)
        }.also {
            DeepLinkManager.clearOrganizationIdToJoin()
            reduce { copy(isJoinLoading = false) }
        }
    }

    private fun refresh() {
        if (currentState.isInitLoading) return
        when(currentState.topBarMenu) {
            HomeTopBarMenu.NOTICE -> { refreshNotice() }
            HomeTopBarMenu.TASK -> { refreshTask() }
        }
    }

    private fun refreshNotice() {
        postSideEffect { HomeSideEffect.Refresh }
        fetchUserInfo()
        getDateNow()
    }

    private fun refreshTask() {
        reduce { copy(isTaskLoading = true) }
    }

    private fun showSnackBar(@StringRes stringId: Int) {
        postSideEffect {
            HomeSideEffect.ShowSnackBar(stringId)
        }
    }
}