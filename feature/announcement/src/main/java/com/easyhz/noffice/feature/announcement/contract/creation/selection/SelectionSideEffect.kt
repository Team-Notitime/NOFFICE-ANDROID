package com.easyhz.noffice.feature.announcement.contract.creation.selection

import com.easyhz.noffice.core.common.base.UiSideEffect

sealed class SelectionSideEffect : UiSideEffect() {
    data object NavigateToUp: SelectionSideEffect()
    data object NavigateToNext: SelectionSideEffect()
}