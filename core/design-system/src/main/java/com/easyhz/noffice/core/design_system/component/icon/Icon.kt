package com.easyhz.noffice.core.design_system.component.icon

import androidx.annotation.DrawableRes
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

@Composable
fun Icon(
    @DrawableRes resId: Int,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current
) {
    androidx.compose.material3.Icon(
        painter = painterResource(id = resId),
        contentDescription = contentDescription,
        modifier = modifier,
        tint = tint
    )
}