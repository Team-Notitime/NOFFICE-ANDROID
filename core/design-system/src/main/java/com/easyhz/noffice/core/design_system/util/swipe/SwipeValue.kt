package com.easyhz.noffice.core.design_system.util.swipe

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.theme.Red

data class SwipeValue(
    val backgroundColor: Color,
    val text: String,
    val alignment: Alignment,
    val roundedCornerShape: RoundedCornerShape,
    val onClick: () -> Unit,
) {
    companion object {
        fun delete(text: String, onClick: () -> Unit) = SwipeValue(
            backgroundColor = Red,
            text = text,
            alignment = Alignment.CenterStart,
            roundedCornerShape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp),
            onClick = onClick
        )
    }
}
