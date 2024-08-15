package com.easyhz.noffice.feature.organization.screen.detail

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.core.model.organization.announcement.OrganizationAnnouncement
import com.easyhz.noffice.domain.organization.usecase.announcement.FetchAnnouncementsByOrganizationUseCase
import com.easyhz.noffice.domain.organization.usecase.organization.FetchOrganizationInfoUseCase
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
    private val fetchOrganizationInfoUseCase: FetchOrganizationInfoUseCase,
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
        fetchData(id)
    }

    // FIXME
    private fun fetchData(id: Int) = viewModelScope.launch {
        fetchOrganizationInfoUseCase.invoke(id).onSuccess {
            reduce {
                copy(
                    organizationInformation = it,
                    isLoading = false
                )
            }
            fetchAnnouncements(it.id)
        }.onFailure {

        }
    }

    private suspend fun fetchAnnouncements(organizationId: Int) {
        fetchAnnouncementsByOrganizationUseCase(organizationId, 1).distinctUntilChanged()
            .cachedIn(viewModelScope).collectLatest {
                _announcementState.value = it
            }
    }

    private fun onClickAnnouncement(id: Int, title: String) {
        postSideEffect {
            DetailSideEffect.NavigateToAnnouncementDetail(id, title)
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
}