package com.easyhz.noffice.feature.home.contract.task

import com.easyhz.noffice.core.common.base.UiIntent

sealed class TaskIntent: UiIntent() {
    data object Refresh: TaskIntent()
}