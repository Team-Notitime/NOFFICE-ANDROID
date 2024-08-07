package com.easyhz.noffice.feature.organization.contract.standby

import com.easyhz.noffice.core.common.base.UiSideEffect

sealed class StandbyMemberSideEffect: UiSideEffect() {
    data object NavigateToUp: StandbyMemberSideEffect()
}