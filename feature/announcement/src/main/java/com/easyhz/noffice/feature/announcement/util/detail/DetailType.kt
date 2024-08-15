package com.easyhz.noffice.feature.announcement.util.detail

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.theme.Green400
import com.easyhz.noffice.core.design_system.theme.Yellow400

enum class DetailType(
    @StringRes val stringId: Int,
    @DrawableRes val iconId: Int,
    val backgroundColor: Color,
    val hasDetail: Boolean,
) {
    DATE_TIME(
        stringId = R.string.announcement_detail_date_time,
        iconId = R.drawable.ic_calendar,
        backgroundColor = Green400,
        hasDetail = false
    ), PLACE(
        stringId = R.string.announcement_detail_place,
        iconId = R.drawable.ic_mappin,
        backgroundColor = Yellow400,
        hasDetail = true
    )
}