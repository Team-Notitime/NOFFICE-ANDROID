package com.easyhz.noffice.feature.announcement.util.creation

import com.easyhz.noffice.feature.announcement.contract.creation.Options
import com.easyhz.noffice.feature.announcement.contract.creation.datetime.SelectionDateTimeState
import com.easyhz.noffice.feature.announcement.contract.creation.place.ContactState

sealed class OptionData<T> {
    abstract val type: Options
    abstract val value: T
    abstract val selected: Boolean

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
}

fun initOptions(): LinkedHashMap<Options, OptionData<*>> {
    return linkedMapOf(
        Options.DATE_TIME to OptionData.DateTime(data = SelectionDateTimeState(), isSelected = false),
        Options.PLACE to OptionData.Place(data = ContactState(), isSelected = false),
    )
}