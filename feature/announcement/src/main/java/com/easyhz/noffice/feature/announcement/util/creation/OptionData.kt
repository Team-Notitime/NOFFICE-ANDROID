package com.easyhz.noffice.feature.announcement.util.creation

import com.easyhz.noffice.feature.announcement.contract.creation.Options
import com.easyhz.noffice.feature.announcement.contract.creation.place.ContactState

sealed class OptionData<T> {
    abstract val type: Options
    abstract val value: T
    abstract val selected: Boolean

    data class Place(val data: ContactState, val isSelected: Boolean) : OptionData<ContactState>() {
        override val type: Options
            get() = Options.PLACE
        override val value: ContactState
            get() = data
        override val selected: Boolean
            get() = isSelected
    }
}

fun initOptions(): HashMap<Options, OptionData<*>> {
    return hashMapOf(
        Options.PLACE to OptionData.Place(data = ContactState(), isSelected = false),
    )
}