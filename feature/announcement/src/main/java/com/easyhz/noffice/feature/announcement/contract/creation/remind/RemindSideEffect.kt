package com.easyhz.noffice.feature.announcement.contract.creation.remind

import com.easyhz.noffice.core.common.base.UiSideEffect
import com.easyhz.noffice.feature.announcement.util.creation.OptionData

sealed class RemindSideEffect: UiSideEffect() {
    data object NavigateToUp: RemindSideEffect()
    data class NavigateToNext(val data: OptionData<List<String>>): RemindSideEffect()
}