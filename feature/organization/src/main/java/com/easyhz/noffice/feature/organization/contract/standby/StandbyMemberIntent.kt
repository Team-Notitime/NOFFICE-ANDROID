package com.easyhz.noffice.feature.organization.contract.standby

import com.easyhz.noffice.core.common.base.UiIntent

sealed class StandbyMemberIntent: UiIntent() {
    data object ClickBackButton: StandbyMemberIntent()
    data object ClickLeftButton: StandbyMemberIntent()
    data object ClickRightButton: StandbyMemberIntent()
}