package com.easyhz.noffice.feature.my_page.contract.detail.consent

import com.easyhz.noffice.core.common.base.UiSideEffect

sealed class ConsentSideEffect: UiSideEffect() {
    data object NavigateToUp: ConsentSideEffect()
}