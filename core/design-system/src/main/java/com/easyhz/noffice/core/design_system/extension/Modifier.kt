package com.easyhz.noffice.core.design_system.extension

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.screenHorizonPadding(): Modifier = padding(horizontal = 16.dp)

inline fun Modifier.noRippleClickable(
    interactionSource: MutableInteractionSource? = null,
    enabled: Boolean = true,
    crossinline onClick: () -> Unit,
): Modifier = composed {
    clickable(
        indication = null,
        enabled = enabled,
        interactionSource = interactionSource ?: remember { MutableInteractionSource() }) {
        onClick()
    }
}

fun Modifier.borderBottom(color: Color, width: Dp): Modifier = this.drawBehind {
    val strokeWidth = width.toPx()
    val y = size.height - strokeWidth / 2

    drawLine(
        color = color,
        start = androidx.compose.ui.geometry.Offset(0f, y),
        end = androidx.compose.ui.geometry.Offset(size.width, y),
        strokeWidth = strokeWidth
    )
}