package com.easyhz.noffice.feature.organization.screen.member

import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.core.model.organization.member.MemberType
import com.easyhz.noffice.feature.organization.contract.member.MemberIntent
import com.easyhz.noffice.feature.organization.contract.member.MemberSideEffect
import com.easyhz.noffice.feature.organization.contract.member.MemberState
import com.easyhz.noffice.feature.organization.util.member.MemberViewType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MemberViewModel @Inject constructor(

) : BaseViewModel<MemberState, MemberIntent, MemberSideEffect>(
    initialState = MemberState.init()
) {
    override fun handleIntent(intent: MemberIntent) {
        when (intent) {
            is MemberIntent.ClickBackButton -> { onClickBackButton() }
            is MemberIntent.ClickLeftButton -> { onClickLeftButton() }
            is MemberIntent.ClickRightButton -> { onClickRightButton() }
            is MemberIntent.HideBottomSheet -> { hideBottomSheet() }
            is MemberIntent.CompleteHideBottomSheet -> { completeHideBottomSheet() }
            is MemberIntent.ClickAuthorityMemberType -> { onClickAuthorityMemberType(intent.type) }
            is MemberIntent.ClickAuthorityButton -> { onClickAuthorityButton() }
        }
    }

    private fun initScreen() {

    }

    private fun onClickBackButton() {
        when (currentState.viewType) {
            MemberViewType.MANAGEMENT -> { navigateToUp() }
            MemberViewType.EDIT -> {
                reduce { copy(viewType = MemberViewType.MANAGEMENT) }
            }
        }
    }

    private fun onClickLeftButton() {
        when (currentState.viewType) {
            MemberViewType.MANAGEMENT -> { }
            MemberViewType.EDIT -> { }
        }
    }

    private fun onClickRightButton() {
        when (currentState.viewType) {
            MemberViewType.MANAGEMENT -> {
                reduce { copy(viewType = MemberViewType.EDIT) }
            }
            MemberViewType.EDIT -> {
                reduce { copy(isOpenBottomSheet = true, authorityType = MemberType.LEADER) }
            }
        }
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

    private fun onClickAuthorityButton() {
        //TODO 저장로직
        hideBottomSheet()
    }
}