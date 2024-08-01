package com.easyhz.noffice.core.design_system.util.textField

import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class TextFieldType(
    val verticalAlignment: Alignment.Vertical,
    val minHeight: Dp,
    val verticalPadding: Dp,
) {
    SINGLE(
        verticalAlignment = Alignment.CenterVertically,
        minHeight = 48.dp,
        verticalPadding = 0.dp,
    ), MULTIPLE(
        verticalAlignment = Alignment.Top,
        minHeight = 200.dp,
        verticalPadding = 14.dp
    )
}