package com.easyhz.noffice.feature.announcement.screen.detail

import androidx.lifecycle.viewModelScope
import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.core.model.announcement.Announcement
import com.easyhz.noffice.core.model.organization.OrganizationInformation
import com.easyhz.noffice.domain.announcement.usecase.announcement.FetchAnnouncementUseCase
import com.easyhz.noffice.domain.announcement.usecase.task.FetchAnnouncementTaskUseCase
import com.easyhz.noffice.domain.organization.usecase.organization.FetchOrganizationUseCase
import com.easyhz.noffice.feature.announcement.contract.detail.DetailIntent
import com.easyhz.noffice.feature.announcement.contract.detail.DetailSideEffect
import com.easyhz.noffice.feature.announcement.contract.detail.DetailState
import com.easyhz.noffice.feature.announcement.contract.detail.DetailState.Companion.updateDetailTitle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnnouncementDetailViewModel @Inject constructor(
    private val fetchOrganizationUseCase: FetchOrganizationUseCase,
    private val fetchAnnouncementUseCase: FetchAnnouncementUseCase,
    private val fetchAnnouncementTaskUseCase: FetchAnnouncementTaskUseCase,
) : BaseViewModel<DetailState, DetailIntent, DetailSideEffect>(
    initialState = DetailState.init()
) {
    override fun handleIntent(intent: DetailIntent) {
        when (intent) {
            is DetailIntent.InitScreen -> {
                initScreen(intent.organizationId, intent.id, intent.title)
            }

            is DetailIntent.NavigateToUp -> {
                navigateToUp()
            }

            is DetailIntent.ClickPlace -> {
                showBottomSheet()
            }

            is DetailIntent.HideBottomSheet -> {
                hideBottomSheet()
            }

            is DetailIntent.LoadWebView -> {
                onLoadWebView(intent.isLoading)
            }

            is DetailIntent.ClickOpenBrowser -> {
                onClickOpenBrowser()
            }

            is DetailIntent.ClickWebViewBack -> {
                onClickWebViewBack()
            }

            is DetailIntent.CopyUrl -> {
                onCopyUrl()
            }

            is DetailIntent.UpdateCanGoBack -> {
                updateCanGoBack(intent.canGoBack)
            }

            is DetailIntent.CheckTask -> {
                onCheckTask(intent.index)
            }
        }
    }

    private fun initScreen(organizationId: Int, id: Int, title: String) {
        reduce { updateDetailTitle(title = title) }
        fetchData(organizationId, id)
    }

    private fun fetchData(organizationId: Int, id: Int) = viewModelScope.launch {
        val organizationDeferred = async { fetchOrganization(organizationId) }
        val announcementDeferred = async { fetchAnnouncement(id) }
        fetchAnnouncementTask(id)
        val organizationResult = organizationDeferred.await()
        val announcementResult = announcementDeferred.await()

        reduce {
            copy(
                organizationInformation = organizationResult.getOrNull() ?: organizationInformation,
                announcement = announcementResult.getOrNull() ?: announcement,
                isLoading = false
            )
        }
    }

    private suspend fun fetchOrganization(organizationId: Int): Result<OrganizationInformation> {
        return fetchOrganizationUseCase(organizationId)
    }
    private suspend fun fetchAnnouncement(id: Int): Result<Announcement> {
        return fetchAnnouncementUseCase(id)
    }

    private fun fetchAnnouncementTask(id: Int) = viewModelScope.launch {
        fetchAnnouncementTaskUseCase.invoke(id).onSuccess {
            reduce { copy(taskList = it) }
        }
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
        currentState.announcement.placeLinkUrl?.let {
            postSideEffect { DetailSideEffect.OpenBrowser(it) }
        }
    }

    private fun onCopyUrl() {
        currentState.announcement.placeLinkUrl?.let {
            postSideEffect { DetailSideEffect.CopyUrl(it) }
        }
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

    private fun onCheckTask(index: Int) {
//        val updatedTaskList = currentState.detail.taskList.mapIndexed { i, task ->
//            if (i == index) task.copy(isDone = !task.isDone) else task
//        }.sortedWith(compareBy<Task> { it.isDone }.thenBy { it.content })
//        val newState = currentState.detail.copy(taskList = updatedTaskList)
//        reduce { copy(detail = newState) }
    }
}