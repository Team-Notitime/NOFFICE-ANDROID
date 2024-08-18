package com.easyhz.noffice.feature.home.component.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.easyhz.noffice.core.model.organization.announcement.OrganizationAnnouncement
import com.easyhz.noffice.domain.organization.usecase.announcement.FetchAnnouncementsByOrganizationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoticeViewModel @Inject constructor(
    private val fetchAnnouncementsByOrganizationUseCase: FetchAnnouncementsByOrganizationUseCase
) : ViewModel() {
    private val _announcementState =
        MutableStateFlow<PagingData<OrganizationAnnouncement>>(PagingData.empty())
    val announcementState: StateFlow<PagingData<OrganizationAnnouncement>> = _announcementState

    private var isLoaded = false

    fun fetchAnnouncementByOrganization(id: Int) = viewModelScope.launch {
        if (isLoaded) return@launch
        fetchAnnouncementsByOrganizationUseCase(id)
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collectLatest {
                _announcementState.value = it
                isLoaded = true
            }
    }

}