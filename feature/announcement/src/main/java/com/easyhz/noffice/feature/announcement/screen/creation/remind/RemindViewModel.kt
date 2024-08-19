package com.easyhz.noffice.feature.announcement.screen.creation.remind

import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.feature.announcement.contract.creation.remind.RemindIntent
import com.easyhz.noffice.feature.announcement.contract.creation.remind.RemindSideEffect
import com.easyhz.noffice.feature.announcement.contract.creation.remind.RemindState
import com.easyhz.noffice.feature.announcement.contract.creation.remind.RemindState.Companion.initRemindMap
import com.easyhz.noffice.feature.announcement.contract.creation.remind.RemindState.Companion.toggleMap
import com.easyhz.noffice.feature.announcement.util.creation.OptionData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RemindViewModel @Inject constructor(

): BaseViewModel<RemindState, RemindIntent, RemindSideEffect>(
    initialState = RemindState.init()
) {
    override fun handleIntent(intent: RemindIntent) {
        when(intent) {
            is RemindIntent.InitScreen -> { initScreen(intent.remindList) }
            is RemindIntent.ClickBackButton -> { onClickBackButton() }
            is RemindIntent.ClickSaveButton -> { onClickSaveButton() }
            is RemindIntent.ClickRemindItem -> { onClickRemindItem(intent.key) }
            is RemindIntent.ClickCustomRemindButton -> { onClickCustomRemindButton() }
            is RemindIntent.SaveCustomRemind -> { onClickRemindItem(intent.data) }
        }
    }

    private fun initScreen(remindList: List<String>?) {
        remindList?.let {
            reduce { initRemindMap(remindList) }
        }
    }

    private fun onClickBackButton() {
        postSideEffect { RemindSideEffect.NavigateToUp }
    }

    private fun onClickSaveButton() {
        val selectedRemindList = currentState.remindMap.filter { it.value }.map { it.key }.toList()
        postSideEffect {
            RemindSideEffect.NavigateToNext(
                OptionData.Remind(data = selectedRemindList, isSelected = selectedRemindList.isNotEmpty())
            )
        }
    }

    private fun onClickCustomRemindButton() {
        postSideEffect { RemindSideEffect.NavigateToCustomRemind }
    }

    private fun onClickRemindItem(key: String) {
        reduce { toggleMap(key) }
    }

}