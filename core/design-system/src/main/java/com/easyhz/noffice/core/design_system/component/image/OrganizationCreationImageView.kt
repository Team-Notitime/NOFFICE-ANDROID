package com.easyhz.noffice.core.design_system.component.image

import android.net.Uri
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import com.bumptech.glide.integration.compose.CrossFade
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.easyhz.noffice.core.design_system.theme.Grey300

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun OrganizationCreationImageView(
    modifier: Modifier = Modifier,
    image: Uri,
    emptyContent: @Composable () -> Unit
) {
    AnimatedContent(
        targetState = image,
        label = "imageView",
        transitionSpec = {
            fadeIn(animationSpec = tween(100)) togetherWith
                    fadeOut(animationSpec = tween(100))
        }
    ) {
        when (it) {
            Uri.EMPTY -> {
                emptyContent()
            }
            else -> {
                GlideImage(
                    modifier = modifier,
                    model = image,
                    contentDescription = image.toString(),
                    loading = placeholder(ColorPainter(Grey300)),
                    failure = placeholder(ColorPainter(Grey300)),
                    contentScale = ContentScale.Crop,
                    transition = CrossFade,
                )
            }
        }
    }
}