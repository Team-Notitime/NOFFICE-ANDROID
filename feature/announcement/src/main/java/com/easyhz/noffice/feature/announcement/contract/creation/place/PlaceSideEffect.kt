package com.easyhz.noffice.feature.announcement.contract.creation.place

import com.easyhz.noffice.core.common.base.UiSideEffect
import com.easyhz.noffice.feature.announcement.util.creation.OptionData


sealed class PlaceSideEffect: UiSideEffect() {
    data object NavigateToUp: PlaceSideEffect()
    data class NavigateToNext(val data: OptionData<ContactState>): PlaceSideEffect()
    data object ClearFocus: PlaceSideEffect()
}