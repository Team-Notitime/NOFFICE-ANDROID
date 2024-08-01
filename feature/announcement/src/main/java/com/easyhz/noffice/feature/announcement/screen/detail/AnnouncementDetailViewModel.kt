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
            is DetailIntent.InitScreen -> { initScreen(intent.id, intent.title)}
        }
    }

    private fun initScreen(id: Int, title: String) {
        reduce { updateDetailTitle(title = title) }
        fetchData(id)
    }

    private fun fetchData(id: Int) = viewModelScope.launch {
        // FIXME
        delay(5000)
        reduce { copy(detail = DUMMY, isLoading = false) }
    }

}