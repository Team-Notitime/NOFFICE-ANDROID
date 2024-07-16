package com.easyhz.noffice.core.design_system.util.bottomSheet

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.unit.Dp

@Composable
fun animatePadding(
    targetValue: Dp,
    animationSpec: AnimationSpec<Dp> = tween(durationMillis = 500),
): State<Dp> = animateDpAsState(
    targetValue = targetValue,
    animationSpec = animationSpec,
    label = "padding"
)