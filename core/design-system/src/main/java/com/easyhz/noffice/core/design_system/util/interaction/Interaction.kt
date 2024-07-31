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

@Deprecated(interactionMessage, level = DeprecationLevel.ERROR)
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

@Deprecated(interactionMessage, level = DeprecationLevel.ERROR)
@Composable
fun useInteraction(
    backgroundColor: Color = White,
    pressedColor: Color = Grey100.copy(0.7f)
): Pair<MutableInteractionSource, Color> {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val color by animateColorAsState(
        targetValue = if (isPressed) pressedColor else backgroundColor,
        label = "color"
    )

    return Pair(interactionSource, color)
}


private const val interactionMessage =
    "Manage interactions globally : " +
            "CompositionLocalProvider 으로 관리함 -> 사용 X" +
            "[NofficeLocalProvider.kt, NofficeIndication.kt]"