package com.easyhz.noffice.feature.my_page.screen.detail.notice

import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.feature.my_page.contract.detail.notice.NoticeIntent
import com.easyhz.noffice.feature.my_page.contract.detail.notice.NoticeSideEffect
import com.easyhz.noffice.feature.my_page.contract.detail.notice.NoticeState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoticeViewModel @Inject constructor(

): BaseViewModel<NoticeState, NoticeIntent, NoticeSideEffect>(
    initialState = NoticeState.init()
) {
    override fun handleIntent(intent: NoticeIntent) {
        when(intent) {
            is NoticeIntent.ClickBackButton -> { onClickBackButton() }
            is NoticeIntent.ClickNotice -> { onClickNotice(intent.index) }
        }
    }

    private fun onClickBackButton() {
        postSideEffect { NoticeSideEffect.NavigateToUp }
    }

    private fun onClickNotice(id: Int) {
        postSideEffect { NoticeSideEffect.NavigateToNoticeDetail(currentState.noticeList[id]) }
    }
}