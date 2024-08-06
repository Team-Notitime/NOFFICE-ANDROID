package com.easyhz.noffice.feature.organization.screen.detail

import androidx.lifecycle.viewModelScope
import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.feature.organization.contract.detail.DetailIntent
import com.easyhz.noffice.feature.organization.contract.detail.DetailSideEffect
import com.easyhz.noffice.feature.organization.contract.detail.DetailState
import com.easyhz.noffice.feature.organization.contract.detail.DetailState.Companion.updateOrganizationName
import com.easyhz.noffice.feature.organization.contract.detail.MemberType
import com.easyhz.noffice.feature.organization.util.detail.DUMMY_LIST
import com.easyhz.noffice.feature.organization.util.detail.DUMMY_ORGANIZATION_INFORMATION
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrganizationDetailViewModel @Inject constructor(

) : BaseViewModel<DetailState, DetailIntent, DetailSideEffect>(
    initialState = DetailState.init()
) {
    override fun handleIntent(intent: DetailIntent) {
        when (intent) {
            is DetailIntent.InitScreen -> {
                initScreen(intent.organizationId, intent.organizationName)
            }
            is DetailIntent.ClickAnnouncement -> { onClickAnnouncement(intent.index) }
            is DetailIntent.NavigateToUp -> { navigateToUp() }
        }
    }

    private fun initScreen(id: Int, name: String) {
        reduce { updateOrganizationName(name) }
        fetchData(id)
    }

    // FIXME
    private fun fetchData(id: Int) = viewModelScope.launch {
        delay(2000)
        currentState.numberOfMembers[MemberType.LEADER] = 11
        currentState.numberOfMembers[MemberType.MEMBER] = 34
        reduce { copy(
            organizationInformation = DUMMY_ORGANIZATION_INFORMATION,
            hasWaitingMember = true,
            isLoading = false
        ) }
        delay(1000)
        reduce { copy(announcementList = DUMMY_LIST, isCardLoading = false) }
    }

    private fun onClickAnnouncement(index: Int) {
        val announcement = currentState.announcementList[index]
        postSideEffect { DetailSideEffect.NavigateToAnnouncementDetail(announcement.id, announcement.title) }
    }
    private fun navigateToUp() {
        postSideEffect { DetailSideEffect.NavigateToUp }
    }
}