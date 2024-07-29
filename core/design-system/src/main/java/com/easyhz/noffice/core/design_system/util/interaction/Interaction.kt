package com.easyhz.noffice.core.design_system.util.interaction

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.easyhz.noffice.core.design_system.theme.Grey100
import com.easyhz.noffice.core.design_system.theme.White

@Composable
fun useInteraction(
    scaleDownFactor: Float = 0.95f,
): Pair<MutableInteractionSource, Float> {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) scaleDownFactor else 1f,
        label = "scale"
    )

    return interactionSource to scale
}

@Composable
fun useInteractionWithColor(
    scaleDownFactor: Float = 0.95f,
    backgroundColor: Color = White,
    pressedColor: Color = Grey100.copy(0.7f)
): Triple<MutableInteractionSource, Float, Color> {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) scaleDownFactor else 1f,
        label = "scale"
    )
    val color by animateColorAsState(
        targetValue = if (isPressed) pressedColor else backgroundColor,
        label = "color"
    )

    return Triple(interactionSource, scale, color)
}