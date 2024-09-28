package com.easyhz.noffice.feature.announcement.screen.detail

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.core.common.error.NofficeError
import com.easyhz.noffice.core.common.util.errorLogging
import com.easyhz.noffice.core.model.announcement.Announcement
import com.easyhz.noffice.core.model.announcement.detail.AnnouncementReader
import com.easyhz.noffice.core.model.organization.OrganizationInformation
import com.easyhz.noffice.core.model.organization.member.MemberType
import com.easyhz.noffice.core.model.task.Task
import com.easyhz.noffice.core.model.task.param.toTaskParam
import com.easyhz.noffice.domain.announcement.usecase.announcement.FetchAnnouncementUseCase
import com.easyhz.noffice.domain.announcement.usecase.reader.FetchAnnouncementNonReadersUseCase
import com.easyhz.noffice.domain.announcement.usecase.reader.FetchAnnouncementReadersUseCase
import com.easyhz.noffice.domain.announcement.usecase.task.FetchAnnouncementTaskUseCase
import com.easyhz.noffice.domain.announcement.usecase.task.UpdateTaskStatusUseCase
import com.easyhz.noffice.domain.organization.usecase.organization.FetchOrganizationInfoUseCase
import com.easyhz.noffice.feature.announcement.contract.detail.DetailIntent
import com.easyhz.noffice.feature.announcement.contract.detail.DetailSideEffect
import com.easyhz.noffice.feature.announcement.contract.detail.DetailState
import com.easyhz.noffice.feature.announcement.util.detail.ReaderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnnouncementDetailViewModel @Inject constructor(
    private val fetchOrganizationInfoUseCase: FetchOrganizationInfoUseCase,
    private val fetchAnnouncementUseCase: FetchAnnouncementUseCase,
    private val fetchAnnouncementTaskUseCase: FetchAnnouncementTaskUseCase,
    private val fetchAnnouncementReadersUseCase: FetchAnnouncementReadersUseCase,
    private val fetchAnnouncementNonReadersUseCase: FetchAnnouncementNonReadersUseCase,
    private val updateTaskStatusUseCase: UpdateTaskStatusUseCase
) : BaseViewModel<DetailState, DetailIntent, DetailSideEffect>(
    initialState = DetailState.init()
) {
    override fun handleIntent(intent: DetailIntent) {
        when (intent) {
            is DetailIntent.InitScreen -> {
                initScreen(intent.organizationId, intent.id)
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
            is DetailIntent.ClickReaderType -> { onClickReaderType(intent.readerType, intent.isExpanded) }
            is DetailIntent.PartialExpandBottomSheet -> { partialExpandBottomSheet() }
        }
    }

    private fun initScreen(organizationId: Int, id: Int) {
        fetchData(organizationId, id)
    }

    private fun fetchData(organizationId: Int, id: Int) = viewModelScope.launch {
        val organizationDeferred = async { fetchOrganization(organizationId) }
        val announcementDeferred = async { fetchAnnouncement(id) }
//        fetchAnnouncementTask(id)
        val organizationResult = organizationDeferred.await()
        val announcementResult = announcementDeferred.await()

        reduce {
            copy(
                organizationInformation = organizationResult.getOrNull() ?: organizationInformation,
                announcement = announcementResult.getOrNull() ?: announcement,
                isLoading = false
            )
        }
        fetchAllReaders()
    }

    private suspend fun fetchOrganization(organizationId: Int): Result<OrganizationInformation> {
        return fetchOrganizationInfoUseCase(organizationId)
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

    private fun onClickReaderType(readerType: ReaderType, isExpanded: Boolean) {
        if (!isExpanded) expandBottomSheet()
        if (currentState.selectedReaderType == readerType) return
        reduce { copy(selectedReaderType = readerType) }
    }

    private fun fetchAllReaders() = viewModelScope.launch {
        if (currentState.organizationInformation.role != MemberType.LEADER) return@launch
        val id = currentState.announcement.announcementId
        if (id == -1) return@launch
        val readerDeferred = async { fetchAnnouncementReaders(id) }
        val nonReaderDeferred = async { fetchAnnouncementNonReaders(id) }

        val readerResult = readerDeferred.await()
        val nonReaderResult = nonReaderDeferred.await()

        reduce {
            copy(
                readerList = readerResult.getOrNull()?.memberList ?: emptyList(),
                nonReaderList = nonReaderResult.getOrNull()?.memberList ?: emptyList()
            )
        }
    }

    private suspend fun fetchAnnouncementReaders(id: Int): Result<AnnouncementReader> {
        return fetchAnnouncementReadersUseCase.invoke(id)
    }

    private suspend fun fetchAnnouncementNonReaders(id: Int): Result<AnnouncementReader> {
        return fetchAnnouncementNonReadersUseCase.invoke(id)
    }

    private fun partialExpandBottomSheet() {
        postSideEffect { DetailSideEffect.PartialExpandBottomSheet }
    }

    private fun expandBottomSheet() {
        postSideEffect { DetailSideEffect.ExpandBottomSheet }
    }


    private fun onCheckTask(index: Int) = viewModelScope.launch {
        val task = currentState.taskList[index]
        updateTaskList(index)
        updateTaskStatusUseCase.invoke(task.toTaskParam()).onSuccess {
        }.onFailure {
            if(it is NofficeError.NoContent) {
                Log.d("AnnouncementDetailViewModel", "onCheckTask: NoContent")
                return@onFailure
            }

            errorLogging(this.javaClass.name, "updateTaskStatus", it)
        }
    }

    private fun updateTaskList(index: Int) {
        val updatedTaskList = currentState.taskList.mapIndexed { i, task ->
            if (i == index) task.copy(isDone = !task.isDone) else task
        }.sortedWith(compareBy<Task> { it.isDone }.thenBy { it.content })
        reduce { copy(taskList = updatedTaskList) }
    }
}