package com.easyhz.noffice.feature.organization.screen.standby

import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.feature.organization.contract.standby.StandbyMemberIntent
import com.easyhz.noffice.feature.organization.contract.standby.StandbyMemberSideEffect
import com.easyhz.noffice.feature.organization.contract.standby.StandbyMemberState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StandbyMemberViewModel @Inject constructor(

): BaseViewModel<StandbyMemberState, StandbyMemberIntent, StandbyMemberSideEffect>(
    initialState = StandbyMemberState.init()
) {
    override fun handleIntent(intent: StandbyMemberIntent) {
        when(intent) {
            is StandbyMemberIntent.ClickBackButton -> { onClickBackButton() }
            is StandbyMemberIntent.ClickLeftButton -> { onClickLeftButton() }
            is StandbyMemberIntent.ClickRightButton -> { onClickRightButton() }
        }
    }

    private fun onClickBackButton() {
        postSideEffect { StandbyMemberSideEffect.NavigateToUp }
    }

    private fun onClickLeftButton() {
        /* TODO 전체 선택 로직*/
    }

    private fun onClickRightButton() {
        /* TODO 수락 로직*/
    }
}