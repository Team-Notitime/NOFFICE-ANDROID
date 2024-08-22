package com.easyhz.noffice.core.design_system.component.image

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import com.bumptech.glide.integration.compose.CrossFade
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.easyhz.noffice.core.design_system.theme.Grey50

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun AnnouncementImage(
    modifier: Modifier = Modifier,
    imageUrl: String
) {
    AnimatedContent(targetState = imageUrl, label = "announcementImage") {
        when (it.isBlank() || imageUrl == "null") {
            true -> {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .background(Grey50),
                )
            }
            false -> {
                GlideImage(
                    modifier = modifier,
                    model = imageUrl,
                    contentDescription = imageUrl,
                    loading = placeholder(ColorPainter(Grey50)),
                    failure = placeholder(ColorPainter(Grey50)),
                    contentScale = ContentScale.Crop,
                    transition = CrossFade,
                )
            }
        }
    }
}