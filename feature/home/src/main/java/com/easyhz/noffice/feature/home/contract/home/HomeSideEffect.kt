package com.easyhz.noffice.feature.home.contract.home

import com.easyhz.noffice.core.common.base.UiSideEffect

sealed class HomeSideEffect: UiSideEffect() {
    data object NavigateToMyPage: HomeSideEffect()
}