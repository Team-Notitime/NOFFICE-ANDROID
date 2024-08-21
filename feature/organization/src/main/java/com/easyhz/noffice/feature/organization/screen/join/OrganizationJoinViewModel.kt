package com.easyhz.noffice.feature.organization.screen.join

import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.viewModelScope
import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.core.common.error.handleError
import com.easyhz.noffice.domain.organization.usecase.organization.JoinOrganizationUseCase
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.model.organization.OrganizationSignUpInformation
import com.easyhz.noffice.feature.organization.contract.join.JoinIntent
import com.easyhz.noffice.feature.organization.contract.join.JoinSideEffect
import com.easyhz.noffice.feature.organization.contract.join.JoinState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrganizationJoinViewModel @Inject constructor(
    private val joinOrganizationUseCase: JoinOrganizationUseCase
): BaseViewModel<JoinState, JoinIntent, JoinSideEffect>(
    initialState = JoinState.init()
) {
    override fun handleIntent(intent: JoinIntent) {
        when(intent) {
            is JoinIntent.InitScreen -> { initScreen(intent.organizationSignUpInformation) }
            is JoinIntent.ClickJoinButton -> { onClickJoinButton() }
            is JoinIntent.ClickBackButton -> { onClickBackButton() }
        }
    }

    private fun initScreen(organizationSignUpInformation: OrganizationSignUpInformation) {
        // TODO Fetch
        reduce { copy(organizationSignUpInformation = organizationSignUpInformation) }
    }

    private fun onClickJoinButton() = viewModelScope.launch {
        joinOrganizationUseCase.invoke(currentState.organizationSignUpInformation.organizationId).onSuccess {
            showSnackBar(R.string.organization_join_success_request)
            postSideEffect { JoinSideEffect.NavigateToUp }
        }.onFailure {
            Log.d(this.javaClass.name, "onClickJoinButton - $it")
            showSnackBar(it.handleError())
        }
    }

    private fun onClickBackButton() {
        postSideEffect { JoinSideEffect.NavigateToUp }
    }

    private fun showSnackBar(@StringRes stringId: Int) {
        postSideEffect {
            JoinSideEffect.ShowSnackBar(stringId)
        }
    }
}