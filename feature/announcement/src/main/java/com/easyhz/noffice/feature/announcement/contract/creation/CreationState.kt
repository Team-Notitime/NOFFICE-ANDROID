package com.easyhz.noffice.feature.announcement.contract.creation

import androidx.annotation.StringRes
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.input.TextFieldValue
import com.easyhz.noffice.core.common.base.UiState
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.feature.announcement.util.creation.OptionData
import com.easyhz.noffice.feature.announcement.util.creation.initOptions

data class CreationState(
    val organizationList: List<String>,
    val selectedOrganization: String,
    val healthCheck: Int,
    val title: String,
    val content: TextFieldValue,
    val taskList: List<String>,
    val optionState: LinkedHashMap<Options, OptionData<*>>,
    val layoutResult: TextLayoutResult?,
    val cursorOffset: Offset,
    val absoluteCursorY: Int,
    val isFocused: Boolean,
    val isMoved: Boolean,
) : UiState() {
    companion object {
        fun init() = CreationState(
            organizationList = listOf("나의 동아리", "나의 그룹", "나의 소모임", "나의 스터디"),
            selectedOrganization = "",
            healthCheck = 0,
            title = "",
            content = TextFieldValue(""),
            taskList = emptyList(),
            optionState = initOptions(),
            layoutResult = null,
            cursorOffset = Offset.Zero,
            absoluteCursorY = 0,
            isFocused = false,
            isMoved = false
        )
    }

    fun <T> getOptionValue(type: Options): T? {
        return (optionState[type] as? OptionData<T>)?.takeIf { it.selected }?.value
    }
}


enum class Options(
    @StringRes val stringId: Int,
) {
    DATE_TIME(
        stringId = R.string.announcement_creation_option_date_time
    ),
    PLACE(
        stringId = R.string.announcement_creation_option_place
    ),
    TASK(
        stringId = R.string.announcement_creation_option_task
    ),
    NOTIFICATION(
        stringId = R.string.announcement_creation_option_notification
    )
}