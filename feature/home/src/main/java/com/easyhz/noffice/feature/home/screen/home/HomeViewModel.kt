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
import com.easyhz.noffice.core.design_system.util.topBar.TopBarIconMenu
import com.easyhz.noffice.core.model.organization.Organization
import com.easyhz.noffice.domain.home.usecase.member.FetchUserInfoUseCase
import com.easyhz.noffice.domain.organization.usecase.organization.FetchOrganizationSignUpInfoUseCase
import com.easyhz.noffice.domain.organization.usecase.organization.FetchOrganizationsUseCase
import com.easyhz.noffice.core.design_system.R
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
    private val fetchUserInfoUseCase: FetchUserInfoUseCase,
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
        }
    }

    init {
        fetchUserInfo()
        fetchDayOfWeek()
        fetchOrganizations()
    }

    private fun fetchUserInfo() = viewModelScope.launch {
        fetchUserInfoUseCase.invoke(Unit).onSuccess {
            reduce { copy(userInfo = it, name = it.alias) }
        }.onFailure {
            // TODO FAIL 처리
        }
    }

    private fun fetchDayOfWeek() {
       val dayOfWeek = DateFormat.getDayOfWeek()
        reduce { copy(dayOfWeek = dayOfWeek) }
    }

    private fun onChangeTopBarMenu(topBarMenu: HomeTopBarMenu) {
        reduce { copy(topBarMenu = topBarMenu) }
    }

    private fun onClickTopBarIconMenu(iconMenu: TopBarIconMenu) {
        when(iconMenu) {
            TopBarIconMenu.NOTIFICATION -> { }
            TopBarIconMenu.USER -> { navigateToMyPageScreen() }
        }
    }

    private fun navigateToMyPageScreen() {
        postSideEffect { HomeSideEffect.NavigateToMyPage }
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
        reduce { copy(isLoading = true) }
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
            DeepLinkManager.setOrganizationIdToJoin(-1)
            reduce { copy(isLoading = false) }
        }
    }

    private fun showSnackBar(@StringRes stringId: Int) {
        postSideEffect {
            HomeSideEffect.ShowSnackBar(stringId)
        }
    }
}