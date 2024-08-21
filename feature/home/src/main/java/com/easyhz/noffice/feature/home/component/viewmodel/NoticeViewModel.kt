package com.easyhz.noffice.feature.home.component.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.easyhz.noffice.core.common.util.DateFormat
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
    private val _announcementStates = mutableMapOf<Int, MutableStateFlow<PagingData<OrganizationAnnouncement>>>()
    private val _isDataLoaded = mutableMapOf<Int, Boolean>()

    fun getAnnouncementStateByOrganization(organizationId: Int): StateFlow<PagingData<OrganizationAnnouncement>> {
        return _announcementStates.getOrPut(organizationId) {
            MutableStateFlow(PagingData.empty())
        }
    }
    fun fetchAnnouncementByOrganization(id: Int) = viewModelScope.launch {
        if (_isDataLoaded[id] == true) {
            return@launch
        }
        fetchAnnouncementsByOrganizationUseCase(id, pattern = DateFormat.PATTERN.FULL)
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collectLatest {
                _announcementStates[id]?.value = it
                _isDataLoaded[id] = true
            }
    }

}