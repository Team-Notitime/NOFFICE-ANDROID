package com.easyhz.noffice.core.design_system.component.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.bumptech.glide.integration.compose.CrossFade
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.easyhz.noffice.core.design_system.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun AnnouncementImage(
    modifier: Modifier = Modifier,
    imageUrl: String
) {
    if (imageUrl.isBlank() || imageUrl == "null") {
        Image(
            modifier = modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.img_illust_1),
            contentScale = ContentScale.Crop,
            contentDescription = "image"
        )
    } else {
        GlideImage(
            modifier = modifier,
            model = imageUrl,
            contentDescription = imageUrl,
            loading = placeholder(R.drawable.img_illust_1),
            failure = placeholder(R.drawable.img_illust_1),
            contentScale = ContentScale.Crop,
            transition = CrossFade,
        )
    }
}