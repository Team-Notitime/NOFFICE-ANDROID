package com.easyhz.noffice.core.design_system.component.button

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.extension.noRippleClickable
import com.easyhz.noffice.core.design_system.theme.Green500
import com.easyhz.noffice.core.design_system.theme.Grey100
import com.easyhz.noffice.core.design_system.theme.Grey50
import com.easyhz.noffice.core.design_system.theme.Grey600
import com.easyhz.noffice.core.design_system.theme.SemiBold18
import com.easyhz.noffice.core.design_system.theme.White

@Composable
fun MediumButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    contentColor: Color = White,
    containerColor: Color = Green500,
    textStyle: TextStyle = SemiBold18,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(targetValue = if (isPressed && enabled) 0.95f else 1f, label = "scale")
    val color by animateColorAsState(targetValue = if(isPressed) Grey50.copy(0.3f) else if(enabled) containerColor else Grey100,
        label = "color"
    )
    val onClickInvoke: () -> Unit = remember(enabled, onClick) {
        if (enabled) onClick else { { } }
    }
    val backgroundColor = remember(enabled, containerColor, isPressed) {
        if (enabled) containerColor else Grey100
    }
    val textColor = remember(enabled, contentColor) {
        if (enabled) contentColor else Grey600
    }

    Box(
        modifier = modifier
            .scale(scale)
            .imePadding()
            .height(54.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .background(color)
            .noRippleClickable(interactionSource) { onClickInvoke() },
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = text,
            style = textStyle,
            color = textColor
        )
    }
}


@Preview(group = "button", name = "enabled")
@Composable
private fun MediumButtonEnabledPrev() {
    MediumButton(
        modifier = Modifier.fillMaxWidth(),
        text = "다음",
        enabled = true
    ) { }
}

@Preview(group = "button", name = "disabled")
@Composable
private fun MediumButtonDisabledPrev() {
    MediumButton(
        modifier = Modifier.fillMaxWidth(),
        text = "다음",
        enabled = false
    ) { }
}