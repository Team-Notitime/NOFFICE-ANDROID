package com.easyhz.noffice.core.design_system.util.timePicker


enum class TimePickerType(val range: IntRange) {
    HOUR(
        range = 1..12
    ),
    MINUTE(
        range = 0..59
    )
}

internal val isAmMap = hashMapOf(
    true to "AM",
    false to "PM"
)