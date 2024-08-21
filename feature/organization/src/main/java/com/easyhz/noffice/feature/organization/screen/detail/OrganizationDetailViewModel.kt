package com.easyhz.noffice.feature.organization.screen.detail

import androidx.annotation.StringRes
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.core.common.error.handleError
import com.easyhz.noffice.core.common.util.DateFormat
import com.easyhz.noffice.core.common.util.errorLogging
import com.easyhz.noffice.core.model.organization.announcement.OrganizationAnnouncement
import com.easyhz.noffice.domain.organization.usecase.announcement.FetchAnnouncementsByOrganizationUseCase
import com.easyhz.noffice.domain.organization.usecase.organization.FetchOrganizationUseCase
import com.easyhz.noffice.feature.organization.contract.detail.DetailIntent
import com.easyhz.noffice.feature.organization.contract.detail.DetailSideEffect
import com.easyhz.noffice.feature.organization.contract.detail.DetailState
import com.easyhz.noffice.feature.organization.contract.detail.DetailState.Companion.updateOrganizationName
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrganizationDetailViewModel @Inject constructor(
    private val fetchOrganizationUseCase: FetchOrganizationUseCase,
    private val fetchAnnouncementsByOrganizationUseCase: FetchAnnouncementsByOrganizationUseCase
) : BaseViewModel<DetailState, DetailIntent, DetailSideEffect>(
    initialState = DetailState.init()
) {
    private val _announcementState : MutableStateFlow<PagingData<OrganizationAnnouncement>> = MutableStateFlow(value = PagingData.empty())
    val announcementState: MutableStateFlow<PagingData<OrganizationAnnouncement>> get() = _announcementState


    override fun handleIntent(intent: DetailIntent) {
        when (intent) {
            is DetailIntent.InitScreen -> {
                initScreen(intent.organizationId, intent.organizationName)
            }

            is DetailIntent.ClickAnnouncement -> {
                onClickAnnouncement(intent.id, intent.title)
            }

            is DetailIntent.NavigateToUp -> {
                navigateToUp()
            }

            is DetailIntent.ClickEditButton -> {
                onClickEditButton()
            }

            is DetailIntent.ClickStandbyMemberButton -> {
                onClickStandbyMemberButton()
            }
        }
    }

    private fun initScreen(id: Int, name: String) {
        reduce { updateOrganizationName(name) }
        initData(id)
    }

    private fun initData(id: Int) = viewModelScope.launch {
        launch { fetchAnnouncements(id) }
        fetchOrganizationUseCase.invoke(id).onSuccess {
            reduce { copy(organizationInformation = it, isLoading = false) }
        }.onFailure {
            errorLogging(this.javaClass.name, "fetchData", it)
            showSnackBar(it.handleError())
        }
    }

    private suspend fun fetchAnnouncements(organizationId: Int) {
        fetchAnnouncementsByOrganizationUseCase(organizationId, DateFormat.PATTERN.DAY).distinctUntilChanged()
            .cachedIn(viewModelScope).collectLatest {
                _announcementState.value = it
                if(currentState.isCardLoading) {
                    reduce { copy(isCardLoading = false) }
                }
            }
    }

    private fun onClickAnnouncement(id: Int, title: String) {
        postSideEffect {
            DetailSideEffect.NavigateToAnnouncementDetail(currentState.organizationInformation.id, id, title)
        }
    }

    private fun navigateToUp() {
        postSideEffect { DetailSideEffect.NavigateToUp }
    }

    private fun onClickEditButton() {
        postSideEffect {
            DetailSideEffect.NavigateToOrganizationManagement(
                currentState.organizationInformation
            )
        }
    }

    private fun onClickStandbyMemberButton() {
        postSideEffect { DetailSideEffect.NavigateToStandbyMember(currentState.organizationInformation.id) }
    }

    private fun showSnackBar(@StringRes stringId: Int) {
        postSideEffect {
            DetailSideEffect.ShowSnackBar(stringId)
        }
    }
}