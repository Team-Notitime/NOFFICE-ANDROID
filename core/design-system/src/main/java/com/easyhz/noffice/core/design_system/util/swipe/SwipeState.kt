package com.easyhz.noffice.core.design_system.util.swipe

import androidx.compose.animation.core.exponentialDecay
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun rememberAnchoredDraggableState(
    velocityThresholdDp: Float,
    positionalThresholdWeight: Float,
): AnchoredDraggableState<DragValue> {
    val density = LocalDensity.current
    return remember {
        AnchoredDraggableState(
            initialValue = DragValue.Center,
            positionalThreshold = { distance: Float -> distance * positionalThresholdWeight },
            velocityThreshold = { with(density) { velocityThresholdDp.dp.toPx() } },
            snapAnimationSpec = spring(),
            decayAnimationSpec = exponentialDecay(),
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
fun <T> AnchoredDraggableState<T>.moveToCenter() {
    dispatchRawDelta(-offset)
}