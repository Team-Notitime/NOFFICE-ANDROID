package com.easyhz.noffice.feature.organization.util.creation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.theme.Green700
import com.easyhz.noffice.core.design_system.theme.Grey500

enum class PromotionCaptionType(
    @StringRes val titleId: Int,
    @DrawableRes val iconId: Int?,
    val color: Color,
    val horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(8.dp)
) {
    VALID(
        titleId = R.string.organization_creation_promotion_caption_valid,
        iconId = R.drawable.ic_check,
        color = Green700
    ), INVALID(
        titleId = R.string.organization_creation_promotion_caption_invalid,
        iconId = R.drawable.ic_x,
        color = Color.Red
    ), LOADING(
        titleId = R.string.organization_creation_promotion_caption_loading,
        iconId = null,
        color = Grey500,
        horizontalArrangement = Arrangement.spacedBy(0.dp)
    )
}