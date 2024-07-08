package com.easyhz.noffice.core.design_system.util

import androidx.annotation.DrawableRes

interface BottomMenu {
    @get:DrawableRes
    val iconId: Int
    val label: String
}