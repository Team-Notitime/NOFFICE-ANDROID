package com.easyhz.noffice.feature.announcement.util.creation

import com.easyhz.noffice.feature.announcement.contract.creation.Options
import com.easyhz.noffice.feature.announcement.contract.creation.datetime.SelectionDateTimeState
import com.easyhz.noffice.feature.announcement.contract.creation.place.ContactState

sealed class OptionData<T> {
    abstract val type: Options
    abstract val value: T
    abstract val selected: Boolean

    companion object {
        fun initOptions(): LinkedHashMap<Options, OptionData<*>> {
            return linkedMapOf(
                Options.DATE_TIME to DateTime(data = SelectionDateTimeState(), isSelected = false),
                Options.PLACE to Place(data = ContactState(), isSelected = false),
//                Options.TASK to Task(data = emptyList(), isSelected = false),
                Options.REMIND to Remind(data = emptyList(), isSelected = false)
            )
        }
    }

    data class DateTime(val data: SelectionDateTimeState, val isSelected: Boolean) : OptionData<SelectionDateTimeState>() {
        override val type: Options
            get() = Options.DATE_TIME
        override val value: SelectionDateTimeState
            get() = data
        override val selected: Boolean
            get() = isSelected
    }

    data class Place(val data: ContactState, val isSelected: Boolean) : OptionData<ContactState>() {
        override val type: Options
            get() = Options.PLACE
        override val value: ContactState
            get() = data
        override val selected: Boolean
            get() = isSelected
    }

    data class Task(val data: List<String>, val isSelected: Boolean): OptionData<List<String>>() {
        override val type: Options
            get() = Options.TASK
        override val value: List<String>
            get() = data
        override val selected: Boolean
            get() = isSelected
    }

    data class Remind(val data: List<String>, val isSelected: Boolean): OptionData<List<String>>() {
        override val type: Options
            get() = Options.REMIND
        override val value: List<String>
            get() = data
        override val selected: Boolean
            get() = isSelected
    }
}