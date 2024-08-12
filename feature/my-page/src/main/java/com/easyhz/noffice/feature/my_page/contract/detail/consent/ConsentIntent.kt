package com.easyhz.noffice.feature.my_page.contract.detail.consent

import com.easyhz.noffice.core.common.base.UiIntent

sealed class ConsentIntent: UiIntent() {
    data object ClickBackButton: ConsentIntent()
    data object ClickToggleButton: ConsentIntent()
}