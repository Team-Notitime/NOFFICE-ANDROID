package com.easyhz.noffice.core.design_system.util.card

import androidx.annotation.DrawableRes

data class CardDetailInfo(
    @DrawableRes val iconId: Int,
    val text: String,
    val description: String
)