package com.easyhz.noffice.core.design_system.util.bottomSheet

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.theme.Grey800
import com.easyhz.noffice.core.design_system.theme.Red

enum class ImageSelectionBottomSheetItem(
    @DrawableRes val iconId: Int,
    @StringRes val stringId: Int,
    val color: Color,
) {
    GALLERY(
        iconId = R.drawable.ic_album,
        stringId = R.string.organization_creation_image_selection_gallery,
        color = Grey800
    ), CAMERA(
        iconId = R.drawable.ic_camera,
        stringId = R.string.organization_creation_image_selection_camera,
        color = Grey800
    ), DELETE(
        iconId = R.drawable.ic_trash,
        stringId = R.string.organization_creation_image_selection_delete,
        color = Red
    );
}