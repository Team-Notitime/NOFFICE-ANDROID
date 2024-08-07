package com.easyhz.noffice.feature.organization.contract.member

import com.easyhz.noffice.core.common.base.UiIntent

sealed class MemberIntent: UiIntent() {
    data object NavigateToUp: MemberIntent()
    data object ClickLeftButton: MemberIntent()
    data object ClickRightButton: MemberIntent()
}