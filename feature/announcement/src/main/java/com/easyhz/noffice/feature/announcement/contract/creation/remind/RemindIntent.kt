package com.easyhz.noffice.feature.announcement.contract.creation.remind

import com.easyhz.noffice.core.common.base.UiIntent

sealed class RemindIntent: UiIntent() {
    data class InitScreen(val remindList: List<String>?, val isSelectedDateTime: Boolean): RemindIntent()
    data object ClickBackButton: RemindIntent()
    data object ClickSaveButton: RemindIntent()
    data class ClickRemindItem(val key: String): RemindIntent()
    data object ClickCustomRemindButton: RemindIntent()
    data class SaveCustomRemind(val data: String): RemindIntent()
}