package com.easyhz.noffice.feature.organization.screen.member

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.viewModelScope
import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.core.common.deepLink.toNofficeDeepLink
import com.easyhz.noffice.core.common.error.NofficeError
import com.easyhz.noffice.core.common.error.handleError
import com.easyhz.noffice.core.common.util.errorLogging
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.model.common.Member
import com.easyhz.noffice.core.model.organization.member.MemberType
import com.easyhz.noffice.core.model.organization.param.RegisterMemberParam
import com.easyhz.noffice.domain.organization.usecase.member.ChangeMemberRoleUseCase
import com.easyhz.noffice.domain.organization.usecase.member.FetchOrganizationMembersUseCase
import com.easyhz.noffice.feature.organization.contract.member.MemberIntent
import com.easyhz.noffice.feature.organization.contract.member.MemberSideEffect
import com.easyhz.noffice.feature.organization.contract.member.MemberState
import com.easyhz.noffice.feature.organization.util.member.MemberViewType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemberViewModel @Inject constructor(
    private val fetchOrganizationMembersUseCase: FetchOrganizationMembersUseCase,
    private val changeMemberRoleUseCase: ChangeMemberRoleUseCase,
) : BaseViewModel<MemberState, MemberIntent, MemberSideEffect>(
    initialState = MemberState.init()
) {
    private var originMemberList = emptyList<Member>()
    override fun handleIntent(intent: MemberIntent) {
        when (intent) {
            is MemberIntent.InitScreen -> {
                initScreen(intent.organizationId, intent.imageUrl)
            }

            is MemberIntent.ClickBackButton -> {
                onClickBackButton()
            }

            is MemberIntent.ClickLeftButton -> {
                onClickLeftButton()
            }

            is MemberIntent.ClickRightButton -> {
                onClickRightButton()
            }

            is MemberIntent.HideBottomSheet -> {
                hideBottomSheet()
            }

            is MemberIntent.CompleteHideBottomSheet -> {
                completeHideBottomSheet()
            }

            is MemberIntent.ClickAuthorityMemberType -> {
                onClickAuthorityMemberType(intent.type)
            }

            is MemberIntent.ClickAuthorityButton -> {
                onClickAuthorityButton()
            }

            is MemberIntent.ClickMember -> {
                onClickMember(intent.index)
            }

            is MemberIntent.ClickDialogNegativeButton -> {
                onClickDialogNegativeButton()
            }

            is MemberIntent.ClickSaveButton -> {
                onClickSaveButton()
            }
        }
    }

    private fun initScreen(id: Int, imageUrl: String?) {
        fetchOrganizationMembers(id)
        reduce { copy(organizationId = id, imageUrl = imageUrl) }
    }

    private fun fetchOrganizationMembers(id: Int) = viewModelScope.launch {
        fetchOrganizationMembersUseCase.invoke(id).onSuccess {
            originMemberList = it
            reduce { copy(memberList = mutableStateListOf(*it.toTypedArray())) }
        }.onFailure {
            errorLogging(this.javaClass.name, "fetchOrganizationMembers", it)
        }.also {
            reduce { copy(isLoading = false) }
        }
    }

    private fun onClickBackButton() {
        when (currentState.viewType) {
            MemberViewType.MANAGEMENT -> {
                navigateToUp()
            }

            MemberViewType.EDIT -> {
                handleEditBackButton()
//                reduce {
//                    copy(
//                        viewType = MemberViewType.MANAGEMENT,
//                        memberList = mutableStateListOf(*memberList.map { it.copy(isSelected = false) }
//                            .toTypedArray())
//                    )
//                }
            }

            MemberViewType.STANDBY -> {}
        }
    }

    private fun onClickLeftButton() {
        when (currentState.viewType) {
            MemberViewType.MANAGEMENT -> {
                navigateToInvitation()
            }

            MemberViewType.EDIT -> {}
            MemberViewType.STANDBY -> {}
        }
    }

    private fun onClickRightButton() {
        when (currentState.viewType) {
            MemberViewType.MANAGEMENT -> {
                reduce { copy(viewType = MemberViewType.EDIT) }
            }

            MemberViewType.EDIT -> {
                handleEditRightButton()
            }

            MemberViewType.STANDBY -> {}
        }
    }

    private fun handleEditRightButton() {
        if (!currentState.memberList.any { it.isSelected }) {
            showSnackBar(R.string.organization_management_member_select_message)
            return
        }
        reduce { copy(isOpenBottomSheet = true, authorityType = MemberType.LEADER) }
    }

    private fun onClickMember(index: Int) {
        currentState.memberList[index] =
            currentState.memberList[index].copy(isSelected = !currentState.memberList[index].isSelected)
    }

    private fun navigateToUp() {
        postSideEffect { MemberSideEffect.NavigateToUp }
    }

    private fun onClickAuthorityMemberType(type: MemberType) {
        reduce { copy(authorityType = type) }
    }

    private fun hideBottomSheet() {
        postSideEffect { MemberSideEffect.HideBottomSheet }
    }

    private fun completeHideBottomSheet() {
        reduce { copy(isOpenBottomSheet = false) }
    }

    private fun navigateToInvitation() {
        val url = currentState.organizationId.toNofficeDeepLink()
        postSideEffect {
            MemberSideEffect.NavigateToInitiation(
                url, currentState.imageUrl.toString()
            )
        }
    }

    private fun onClickDialogNegativeButton() {
        reduce { copy(isShowDialog = false) }
        initializeMemberList()
    }

    private fun handleEditBackButton() {
        if (originMemberList == currentState.memberList) {
            initializeMemberList()
        } else {
            reduce { copy(isShowDialog = true) }
        }
    }

    private fun initializeMemberList() {
        reduce {
            copy(
                memberList = mutableStateListOf(*originMemberList.toTypedArray()),
                viewType = MemberViewType.MANAGEMENT
            )
        }
    }

    private fun onClickAuthorityButton() {
        val memberList = currentState.memberList
        memberList.forEachIndexed { index, member ->
            if (member.isSelected) {
                memberList[index] =
                    member.copy(role = currentState.authorityType, isSelected = false)
            }
        }
        hideBottomSheet()
    }

    private fun onClickSaveButton() = viewModelScope.launch {
        val differentRoles = findRoleChanges(originMemberList, currentState.memberList)
        val changeRoleJobs = differentRoles.map { (role, memberId) ->
            async { changeMemberRole(role, memberId) }
        }
        changeRoleJobs.awaitAll()
        fetchOrganizationMembers(currentState.organizationId)
        reduce { copy(viewType = MemberViewType.MANAGEMENT) }
    }

    private suspend fun changeMemberRole(role: MemberType, memberId: List<Int>) {
        val param = RegisterMemberParam(
            organizationId = currentState.organizationId,
            memberIds = memberId,
            role = role
        )
        changeMemberRoleUseCase.invoke(param).onSuccess {
            showSnackBar(R.string.organization_management_member_message)
        }.onFailure {
            if (it is NofficeError.NoContent) {
                Log.d("MemberViewModel", "success")
                return@onFailure
            }
            errorLogging(this.javaClass.name, "changeMemberRole", it)
            showSnackBar(NofficeError.UnexpectedError.handleError())
        }
    }

    private fun showSnackBar(@StringRes stringId: Int) {
        postSideEffect { MemberSideEffect.ShowSnackBar(stringId) }
    }
}

private fun findRoleChanges(
    originList: List<Member>,
    memberList: List<Member>
): Map<MemberType, List<Int>> {
    return originList.indices
        .filter { index -> originList[index].role != memberList[index].role }
        .groupBy(
            keySelector = { index -> memberList[index].role },
            valueTransform = { index -> originList[index].id }
        )
}
