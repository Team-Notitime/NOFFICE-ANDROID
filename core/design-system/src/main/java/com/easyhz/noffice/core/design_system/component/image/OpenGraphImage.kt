package com.easyhz.noffice.core.design_system.component.image

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import com.bumptech.glide.integration.compose.CrossFade
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.easyhz.noffice.core.design_system.theme.Yellow300

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun OpenGraphImage(
    modifier: Modifier = Modifier,
    image: String?,
) {
    if (image.isNullOrBlank()) {
        Box(modifier = modifier.fillMaxSize().background(Yellow300))
    } else {
        GlideImage(
            modifier = modifier,
            model = image,
            contentDescription = image,
            loading = placeholder(ColorPainter(Yellow300)),
            failure = placeholder(ColorPainter(Yellow300)),
            contentScale = ContentScale.Crop,
            transition = CrossFade
        )
    }
}