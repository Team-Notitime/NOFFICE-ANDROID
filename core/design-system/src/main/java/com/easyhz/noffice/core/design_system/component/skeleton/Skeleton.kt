package com.easyhz.noffice.core.design_system.component.skeleton

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.easyhz.noffice.core.design_system.extension.skeletonEffect

@Composable
fun Skeleton(
    modifier: Modifier,
) {
    Box(modifier = modifier.skeletonEffect())
}

@Composable
fun SkeletonProvider(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    skeletonContent: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    AnimatedContent(
        modifier = modifier,
        targetState = isLoading,
        transitionSpec = {
            fadeIn(animationSpec = tween(300)) togetherWith fadeOut(animationSpec = tween(300))
        },
        label = "skeleton"
    ) { loadingState ->
        when (loadingState) {
            true -> {
                skeletonContent()
            }
            false -> {
                content()
            }
        }
    }
}