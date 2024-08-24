package com.easyhz.noffice.feature.organization.screen.standby

import androidx.annotation.StringRes
import androidx.lifecycle.viewModelScope
import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.core.common.error.HttpError
import com.easyhz.noffice.core.common.error.NofficeError
import com.easyhz.noffice.core.common.error.handleError
import com.easyhz.noffice.core.common.util.errorLogging
import com.easyhz.noffice.core.model.organization.member.MemberType
import com.easyhz.noffice.core.model.organization.param.RegisterMemberParam
import com.easyhz.noffice.domain.organization.usecase.organization.AcceptRegisterMemberUseCase
import com.easyhz.noffice.domain.organization.usecase.organization.FetchOrganizationPendingMembersUseCase
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.feature.organization.contract.standby.StandbyMemberIntent
import com.easyhz.noffice.feature.organization.contract.standby.StandbyMemberSideEffect
import com.easyhz.noffice.feature.organization.contract.standby.StandbyMemberState
import com.easyhz.noffice.feature.organization.contract.standby.StandbyMemberState.Companion.toggleMember
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StandbyMemberViewModel @Inject constructor(
    private val fetchOrganizationPendingMembersUseCase: FetchOrganizationPendingMembersUseCase,
    private val acceptRegisterMemberUseCase: AcceptRegisterMemberUseCase,
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
            reduce { copy(memberList = it, organizationId = id) }
        }.onFailure {
            errorLogging(this.javaClass.name, "fetchPendingMember", it)
            showSnackBar(it.handleError())
        }.also {
            reduce { copy(isLoading = false) }
        }
    }

    private fun onClickBackButton() {
        postSideEffect { StandbyMemberSideEffect.NavigateToUp }
    }

    private fun onClickLeftButton() {
        reduce { toggleAllMembers() }
    }

    private fun onClickRightButton() = viewModelScope.launch {
        val idList = currentState.memberList.filter { it.isSelected }.map { it.id }
        if(idList.isEmpty()) return@launch
        reduce { copy(isLoading = true) }
        val param = RegisterMemberParam(
            organizationId = currentState.organizationId,
            role = MemberType.PARTICIPANT,
            memberIds = idList
        )
        acceptRegisterMemberUseCase.invoke(param).onSuccess {
            initScreen(param.organizationId)
            showSnackBar(R.string.organization_pending_member_success)
        }.onFailure {
            errorLogging(this.javaClass.name, "acceptRegisterMember", it)
            if(it is NofficeError.NoContent) {
                initScreen(param.organizationId)
                showSnackBar(R.string.organization_pending_member_success)
                return@onFailure
            }
            val message = when(it) {
                is HttpError.ForbiddenError -> R.string.organization_pending_member_forbidden
                is HttpError.NotFoundError -> R.string.organization_pending_member_not_found
                else -> it.handleError()
            }
            showSnackBar(message)
            reduce { copy(isLoading = false) }
        }
    }

    private fun showSnackBar(@StringRes stringId: Int) {
        postSideEffect {
            StandbyMemberSideEffect.ShowSnackBar(stringId)
        }
    }

    private fun onClickMember(index: Int) {
        reduce { toggleMember(index)  }
    }
}