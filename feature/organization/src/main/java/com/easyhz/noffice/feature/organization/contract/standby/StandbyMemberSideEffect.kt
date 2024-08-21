package com.easyhz.noffice.feature.organization.contract.standby

import androidx.annotation.StringRes
import com.easyhz.noffice.core.common.base.UiSideEffect

sealed class StandbyMemberSideEffect: UiSideEffect() {
    data object NavigateToUp: StandbyMemberSideEffect()
    data class ShowSnackBar(@StringRes val stringId: Int): StandbyMemberSideEffect()
}