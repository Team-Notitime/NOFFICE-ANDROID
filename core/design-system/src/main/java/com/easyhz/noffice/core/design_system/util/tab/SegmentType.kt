package com.easyhz.noffice.core.design_system.util.tab

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface SegmentType {
    @get:StringRes
    val labelId: Int
    @get:DrawableRes
    val iconId: Int?
        get() = null
}