package com.easyhz.noffice.feature.announcement.contract.creation.datetime

import com.easyhz.noffice.core.common.base.UiIntent
import java.time.LocalDate

sealed class DateTimeIntent: UiIntent() {
    data class InitScreen(val date: String?, val time: String?): DateTimeIntent()
    data object ClickBackButton: DateTimeIntent()
    data object ClickSaveButton: DateTimeIntent()
    data class SelectDate(val date: LocalDate): DateTimeIntent()
}