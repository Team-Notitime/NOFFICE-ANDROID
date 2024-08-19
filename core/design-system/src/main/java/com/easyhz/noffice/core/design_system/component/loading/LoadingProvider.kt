package com.easyhz.noffice.core.design_system.component.loading

import android.app.Activity
import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.extension.noRippleClickable
import com.easyhz.noffice.core.design_system.theme.Grey200
import com.easyhz.noffice.core.design_system.theme.White

@Composable
fun LoadingScreenProvider(
    isLoading: Boolean = false,
    statusBarColor: Color? = Color.Transparent,
    navigationBarColor: Color? = Color.Transparent,
    content: @Composable () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isLoading
    )
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .noRippleClickable { focusManager.clearFocus() }
    ) {
        content()
        Dim(
            isDim = isLoading,
            dimColor = Grey200.copy(alpha = 0.5f),
            statusBarColor = statusBarColor,
            navigationBarColor = navigationBarColor,
            onDismissRequest = { }
        )
        if (isLoading) {
            LottieAnimation(
                modifier = Modifier.size((screenWidthDp / 5).dp).align(Alignment.Center),
                composition = composition,
                progress = { progress },
            )
        }
    }
}


@Composable
internal fun Dim(
    isDim: Boolean,
    dimColor: Color,
    statusBarColor: Color?,
    navigationBarColor: Color?,
    onDismissRequest: () -> Unit,
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            updateWindowColors(
                view = view,
                isDim = isDim,
                dimColor = dimColor,
                statusBarColor = statusBarColor,
                navigationBarColor = navigationBarColor
            )
        }
    }
    if (isDim) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(dimColor)
                .noRippleClickable { onDismissRequest() }
        )
    }
}

/**
 * statusBar, navigationBar 을 [dimColor] 와 통일.
 *
 * @param view 현재 뷰
 * @param isDim 활성화 여부
 * @param dimColor 어두워지는 색
 * @param statusBarColor statusBar 색 (null 이면 적용 X)
 * @param navigationBarColor navigationBar 색 (null 이면 적용 X)
 */
private fun updateWindowColors(
    view: View,
    isDim: Boolean,
    dimColor: Color,
    statusBarColor: Color? = null,
    navigationBarColor: Color? = null
) {
    val window = (view.context as Activity).window
    statusBarColor?.let {
        window.statusBarColor = if (isDim) dimColor.toArgb() else it.toArgb()
    }

    navigationBarColor?.let {
        window.navigationBarColor = if (isDim) dimColor.toArgb() else it.toArgb()
    }
}


@Preview
@Composable
fun LoadingScreenPreview() {
    LoadingScreenProvider(isLoading = true, statusBarColor = White, navigationBarColor = White) {

    }
}