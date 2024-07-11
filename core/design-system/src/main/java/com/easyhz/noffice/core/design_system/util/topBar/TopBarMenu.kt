package com.easyhz.noffice.core.design_system.util.topBar

import androidx.annotation.DrawableRes
import com.easyhz.noffice.core.design_system.R

interface TopBarMenu {
    val label: String
}

enum class TopBarIconMenu(
    @DrawableRes val iconId: Int,
    val label: String,
){
    BELL(iconId = R.drawable.ic_bell, label = "notification"),
    USER(iconId = R.drawable.ic_user, label = "user")
}