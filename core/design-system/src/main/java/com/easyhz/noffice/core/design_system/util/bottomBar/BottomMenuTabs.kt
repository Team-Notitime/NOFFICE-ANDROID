package com.easyhz.noffice.core.design_system.util.bottomBar

import androidx.annotation.DrawableRes

interface BottomMenu {
    @get:DrawableRes
    val iconId: Int
    val label: String
}