package com.easyhz.noffice.feature.organization.screen.standby

import androidx.lifecycle.viewModelScope
import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.core.common.util.errorLogging
import com.easyhz.noffice.domain.organization.usecase.organization.FetchOrganizationPendingMembersUseCase
import com.easyhz.noffice.feature.organization.contract.standby.StandbyMemberIntent
import com.easyhz.noffice.feature.organization.contract.standby.StandbyMemberSideEffect
import com.easyhz.noffice.feature.organization.contract.standby.StandbyMemberState
import com.easyhz.noffice.feature.organization.contract.standby.StandbyMemberState.Companion.toggleMember
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StandbyMemberViewModel @Inject constructor(
    private val fetchOrganizationPendingMembersUseCase: FetchOrganizationPendingMembersUseCase
): BaseViewModel<StandbyMemberState, StandbyMemberIntent, StandbyMemberSideEffect>(
    initialState = StandbyMemberState.init()
) {
    override fun handleIntent(intent: StandbyMemberIntent) {
        when(intent) {
            is StandbyMemberIntent.InitScreen -> { initScreen(intent.organizationId) }
            is StandbyMemberIntent.ClickBackButton -> { onClickBackButton() }
            is StandbyMemberIntent.ClickLeftButton -> { onClickLeftButton() }
            is StandbyMemberIntent.ClickRightButton -> { onClickRightButton() }
            is StandbyMemberIntent.ClickMember -> { onClickMember(intent.index) }
        }
    }

    private fun initScreen(id: Int) = viewModelScope.launch {
        fetchOrganizationPendingMembersUseCase.invoke(id).onSuccess {
            reduce { copy(memberList = it) }
        }.onFailure {
            errorLogging(this.javaClass.name, "fetchPendingMember", it)
        }
    }

    private fun onClickBackButton() {
        postSideEffect { StandbyMemberSideEffect.NavigateToUp }
    }

    private fun onClickLeftButton() {
        reduce { toggleAllMembers() }
    }

    private fun onClickRightButton() {
        /* TODO 수락 로직*/
    }

    private fun onClickMember(index: Int) {
        reduce { toggleMember(index)  }
    }
}