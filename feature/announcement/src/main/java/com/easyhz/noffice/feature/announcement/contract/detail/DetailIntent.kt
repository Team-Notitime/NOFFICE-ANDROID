package com.easyhz.noffice.feature.announcement.contract.detail

import com.easyhz.noffice.core.common.base.UiIntent

sealed class DetailIntent: UiIntent() {
    data class InitScreen(val id: Int, val title: String): DetailIntent()
    data object ClickPlace: DetailIntent()
    data object HideBottomSheet: DetailIntent()
    data class LoadWebView(val isLoading: Boolean): DetailIntent()
}