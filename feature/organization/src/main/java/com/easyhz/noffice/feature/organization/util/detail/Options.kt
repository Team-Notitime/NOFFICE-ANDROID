package com.easyhz.noffice.feature.organization.util.detail

import androidx.annotation.DrawableRes
import com.easyhz.noffice.core.design_system.R

enum class Options(
    @DrawableRes val iconId: Int
) {
    DATE(
        iconId = R.drawable.ic_calendar
    ), PLACE(
        iconId = R.drawable.ic_mappin
    ), TASK(
        iconId = R.drawable.ic_check
    )
}