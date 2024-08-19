package com.easyhz.noffice.feature.announcement.screen.creation.remind

import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.feature.announcement.contract.creation.remind.RemindIntent
import com.easyhz.noffice.feature.announcement.contract.creation.remind.RemindSideEffect
import com.easyhz.noffice.feature.announcement.contract.creation.remind.RemindState
import com.easyhz.noffice.feature.announcement.contract.creation.remind.RemindState.Companion.initRemindMap
import com.easyhz.noffice.feature.announcement.contract.creation.remind.RemindState.Companion.toggleMap
import com.easyhz.noffice.feature.announcement.contract.creation.remind.timeList
import com.easyhz.noffice.feature.announcement.contract.creation.remind.toRemindMap
import com.easyhz.noffice.feature.announcement.util.creation.OptionData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RemindViewModel @Inject constructor(

): BaseViewModel<RemindState, RemindIntent, RemindSideEffect>(
    initialState = RemindState.init()
) {
    private var isAddedCustom = false
    override fun handleIntent(intent: RemindIntent) {
        when(intent) {
            is RemindIntent.InitScreen -> { initScreen(intent.remindList, intent.isSelectedDateTime) }
            is RemindIntent.ClickBackButton -> { onClickBackButton() }
            is RemindIntent.ClickSaveButton -> { onClickSaveButton() }
            is RemindIntent.ClickRemindItem -> { onClickRemindItem(intent.key) }
            is RemindIntent.ClickCustomRemindButton -> { onClickCustomRemindButton() }
            is RemindIntent.SaveCustomRemind -> { saveCustomRemind(intent.data) }
        }
    }

    private fun initScreen(remindList: List<String>?, isSelectedDateTime: Boolean) {
        var updatedRemindList = remindList

        when {
            !isSelectedDateTime && !isAddedCustom -> {
                reduce { copy(remindMap = LinkedHashMap()) }
            }
            isSelectedDateTime && !isAddedCustom -> {
                reduce { copy(remindMap = currentState.originalMap) }
            }
            isSelectedDateTime -> {
                val mergedMap = mergeMaps(timeList.toRemindMap(), currentState.remindMap)
                reduce { copy(remindMap = mergedMap) }
            }
            remindList != null -> {
                updatedRemindList = remindList.filter { it !in timeList }
                val filteredMap = LinkedHashMap(currentState.remindMap.filter { it.key !in timeList })
                reduce { copy(remindMap = filteredMap) }
            }
        }

        updatedRemindList?.let {
            reduce { initRemindMap(it) }
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

    private fun saveCustomRemind(data: String) {
        reduce { toggleMap(data) }
        isAddedCustom = true
    }

    private fun onClickRemindItem(key: String) {
        reduce { toggleMap(key) }
    }

}

private fun mergeMaps(map1: LinkedHashMap<String, Boolean>, map2: LinkedHashMap<String, Boolean>): LinkedHashMap<String, Boolean> {
    val result = LinkedHashMap<String, Boolean>()
    map1.forEach { (key, value) -> result[key] = value }
    map2.forEach { (key, value) -> result[key] = value }
    return result
}
