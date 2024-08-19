package com.easyhz.noffice.feature.announcement.contract.creation.remind.custom

import com.easyhz.noffice.core.common.base.UiIntent
import java.time.LocalDate

sealed class CustomRemindIntent: UiIntent() {
    data object ClickBackButton: CustomRemindIntent()
    data object ClickSaveButton: CustomRemindIntent()
    data class SelectDate(val date: LocalDate): CustomRemindIntent()
    data class ChangeTimeValue(val hour: Int, val minute: Int, val isAm: Boolean): CustomRemindIntent()
}
