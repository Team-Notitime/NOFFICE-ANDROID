package com.easyhz.noffice.feature.announcement.contract.detail

import com.easyhz.noffice.core.common.base.UiIntent
import com.easyhz.noffice.feature.announcement.util.detail.ReaderType

sealed class DetailIntent: UiIntent() {
    data class InitScreen(val organizationId: Int, val id: Int): DetailIntent()
    data object NavigateToUp: DetailIntent()
    data object ClickPlace: DetailIntent()
    data object HideBottomSheet: DetailIntent()
    data class LoadWebView(val isLoading: Boolean): DetailIntent()
    data object ClickOpenBrowser: DetailIntent()
    data object ClickWebViewBack: DetailIntent()
    data object CopyUrl: DetailIntent()
    data class UpdateCanGoBack(val canGoBack: Boolean): DetailIntent()
    data class CheckTask(val index: Int): DetailIntent()
    data class ClickReaderType(val readerType: ReaderType, val isExpanded: Boolean): DetailIntent()
    data object PartialExpandBottomSheet: DetailIntent()
}