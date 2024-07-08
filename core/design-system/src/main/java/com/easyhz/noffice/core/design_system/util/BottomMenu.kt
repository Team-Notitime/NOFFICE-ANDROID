package com.easyhz.noffice.core.design_system.util

import androidx.annotation.DrawableRes
import com.easyhz.noffice.core.design_system.R

enum class BottomMenu(
    @DrawableRes val iconId: Int,
    val label: String,
) {
    HOME(
        iconId = R.drawable.ic_home,
        label = "home"
    ),
    ADD(
        iconId = R.drawable.ic_add,
        label = "add"
    ),
    ORGANIZATION(
        iconId = R.drawable.ic_group,
        label = "organization"
    )
}