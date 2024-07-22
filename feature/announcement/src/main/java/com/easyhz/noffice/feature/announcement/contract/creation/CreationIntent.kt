package com.easyhz.noffice.feature.announcement.contract.creation

import com.easyhz.noffice.core.common.base.UiIntent

sealed class CreationIntent: UiIntent() {
    data object ClickBackButton: CreationIntent()
    data object ClickNextButton: CreationIntent()
}