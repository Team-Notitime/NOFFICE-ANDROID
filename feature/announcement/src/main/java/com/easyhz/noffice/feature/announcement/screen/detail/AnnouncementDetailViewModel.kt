package com.easyhz.noffice.feature.announcement.screen.detail

import androidx.lifecycle.viewModelScope
import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.feature.announcement.contract.detail.DUMMY
import com.easyhz.noffice.feature.announcement.contract.detail.DetailIntent
import com.easyhz.noffice.feature.announcement.contract.detail.DetailSideEffect
import com.easyhz.noffice.feature.announcement.contract.detail.DetailState
import com.easyhz.noffice.feature.announcement.contract.detail.DetailState.Companion.updateDetailTitle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnnouncementDetailViewModel @Inject constructor(

): BaseViewModel<DetailState, DetailIntent, DetailSideEffect>(
    initialState = DetailState.init()
) {
    override fun handleIntent(intent: DetailIntent) {
        when(intent) {
            is DetailIntent.InitScreen -> { initScreen(intent.id, intent.title) }
            is DetailIntent.NavigateToUp -> { navigateToUp() }
            is DetailIntent.ClickPlace -> { showBottomSheet() }
            is DetailIntent.HideBottomSheet -> { hideBottomSheet() }
            is DetailIntent.LoadWebView -> { onLoadWebView(intent.isLoading) }
            is DetailIntent.ClickOpenBrowser -> { onClickOpenBrowser() }
            is DetailIntent.ClickWebViewBack -> { onClickWebViewBack() }
            is DetailIntent.CopyUrl -> { onCopyUrl() }
            is DetailIntent.UpdateCanGoBack -> { updateCanGoBack(intent.canGoBack) }
        }
    }

    private fun initScreen(id: Int, title: String) {
        reduce { updateDetailTitle(title = title) }
        fetchData(id)
    }

    private fun fetchData(id: Int) = viewModelScope.launch {
        // FIXME
        delay(1000)
        reduce { copy(detail = DUMMY, isLoading = false) }
    }

    private fun navigateToUp() {
        postSideEffect { DetailSideEffect.NavigateToUp }
    }

    private fun showBottomSheet() {
        reduce { copy(isShowBottomSheet = true) }
    }

    private fun hideBottomSheet() {
        reduce { copy(isShowBottomSheet = false) }
    }

    private fun onLoadWebView(isLoading: Boolean) {
        reduce { copy(isWebViewLoading = isLoading) }
    }

    private fun onClickOpenBrowser() {
        postSideEffect { DetailSideEffect.OpenBrowser(currentState.detail.placeUrl) }
    }

    private fun onCopyUrl() {
        postSideEffect { DetailSideEffect.CopyUrl(currentState.detail.placeUrl) }
    }
    private fun onClickWebViewBack() {
        if (currentState.canGoBack) {
            postSideEffect { DetailSideEffect.NavigateToUpInWebView }
        } else {
            hideBottomSheet()
        }
    }

    private fun updateCanGoBack(canGoBack: Boolean) {
        reduce { copy(canGoBack = canGoBack) }
    }
}