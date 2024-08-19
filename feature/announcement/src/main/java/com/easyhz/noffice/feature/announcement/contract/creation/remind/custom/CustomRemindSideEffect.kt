package com.easyhz.noffice.feature.announcement.contract.creation.remind.custom

import com.easyhz.noffice.core.common.base.UiSideEffect

sealed class CustomRemindSideEffect: UiSideEffect() {
    data object NavigateToUp: CustomRemindSideEffect()
    data class SaveToDateTime(val data: String): CustomRemindSideEffect()
}