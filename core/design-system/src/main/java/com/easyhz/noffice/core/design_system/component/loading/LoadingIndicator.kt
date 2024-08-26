package com.easyhz.noffice.core.design_system.component.loading

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.easyhz.noffice.core.design_system.R

@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isLoading
    )
    if (isLoading) {
        LottieAnimation(
            modifier = modifier,
            composition = composition,
            progress = { progress },
        )
    }
}