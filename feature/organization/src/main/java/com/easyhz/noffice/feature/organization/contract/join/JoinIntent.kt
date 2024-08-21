package com.easyhz.noffice.feature.organization.contract.join

import com.easyhz.noffice.core.common.base.UiIntent
import com.easyhz.noffice.core.model.organization.OrganizationSignUpInformation

sealed class JoinIntent: UiIntent() {
    data class InitScreen(val organizationSignUpInformation: OrganizationSignUpInformation): JoinIntent()
    data object ClickJoinButton: JoinIntent()
    data object ClickBackButton: JoinIntent()
}