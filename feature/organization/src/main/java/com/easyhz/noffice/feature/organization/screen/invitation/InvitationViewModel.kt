package com.easyhz.noffice.feature.organization.screen.invitation

import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.feature.organization.contract.invitation.InvitationIntent
import com.easyhz.noffice.feature.organization.contract.invitation.InvitationSideEffect
import com.easyhz.noffice.feature.organization.contract.invitation.InvitationState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InvitationViewModel @Inject constructor(

): BaseViewModel<InvitationState, InvitationIntent, InvitationSideEffect>(
    initialState = InvitationState.init()
) {
    override fun handleIntent(intent: InvitationIntent) {
        when(intent) {
            is InvitationIntent.InitScreen -> { initScreen(intent.invitationUrl, intent.imageUrl) }
            is InvitationIntent.ClickHomeButton -> { onClickHomeButton() }
            is InvitationIntent.ClickCopyUrl -> { onClickCopyUrl() }
        }
    }

    private fun initScreen(invitationUrl: String, imageUrl: String) {
        reduce { copy(invitationUrl = invitationUrl, imageUrl = imageUrl) }
    }

    private fun onClickHomeButton() {
        postSideEffect { InvitationSideEffect.NavigateToHome }
    }

    private fun onClickCopyUrl() {
        postSideEffect { InvitationSideEffect.CopyUrl(currentState.invitationUrl) }
    }
}