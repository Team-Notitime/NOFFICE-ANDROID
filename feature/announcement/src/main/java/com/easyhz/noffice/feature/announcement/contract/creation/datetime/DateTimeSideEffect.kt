package com.easyhz.noffice.feature.announcement.contract.creation.datetime

import com.easyhz.noffice.core.common.base.UiSideEffect
import com.easyhz.noffice.feature.announcement.util.creation.OptionData

sealed class DateTimeSideEffect: UiSideEffect() {
    data object NavigateToUp: DateTimeSideEffect()
    data class NavigateToNext(val data: OptionData<SelectionDateTimeState>): DateTimeSideEffect()
}