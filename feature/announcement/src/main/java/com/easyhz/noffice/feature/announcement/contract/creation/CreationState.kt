package com.easyhz.noffice.feature.announcement.contract.creation

import androidx.annotation.StringRes
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.input.TextFieldValue
import com.easyhz.noffice.core.common.base.UiState
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.feature.announcement.util.creation.OptionData

data class CreationState(
    val title: String,
    val content: TextFieldValue,
    val taskList: List<String>,
    val optionState: LinkedHashMap<Options, OptionData<*>>,
    val layoutResult: TextLayoutResult?,
    val cursorOffset: Offset,
    val absoluteCursorY: Int,
    val isFocused: Boolean,
    val isMoved: Boolean,
    val enabledButton: Boolean,
) : UiState() {
    companion object {
        fun init() = CreationState(
            title = "",
            content = TextFieldValue(""),
            taskList = emptyList(),
            optionState = OptionData.initOptions(),
            layoutResult = null,
            cursorOffset = Offset.Zero,
            absoluteCursorY = 0,
            isFocused = false,
            isMoved = false,
            enabledButton = false
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
    REMIND(
        stringId = R.string.announcement_creation_option_remind
    )
}