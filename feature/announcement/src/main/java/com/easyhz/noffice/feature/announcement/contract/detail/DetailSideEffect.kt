package com.easyhz.noffice.feature.announcement.contract.detail

import com.easyhz.noffice.core.common.base.UiSideEffect

sealed class DetailSideEffect: UiSideEffect() {
    data object NavigateToUp: DetailSideEffect()
    data class OpenBrowser(val url: String): DetailSideEffect()
    data class CopyUrl(val url: String): DetailSideEffect()
    data object NavigateToUpInWebView: DetailSideEffect()
}