package com.easyhz.noffice.feature.organization.contract.join

import androidx.annotation.StringRes
import com.easyhz.noffice.core.common.base.UiSideEffect

sealed class JoinSideEffect: UiSideEffect() {
    data object NavigateToUp: JoinSideEffect()
    data class ShowSnackBar(@StringRes val stringId: Int): JoinSideEffect()
}