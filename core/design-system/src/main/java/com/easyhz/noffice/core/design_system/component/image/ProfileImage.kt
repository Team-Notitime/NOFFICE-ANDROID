package com.easyhz.noffice.core.design_system.component.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.bumptech.glide.integration.compose.CrossFade
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.easyhz.noffice.core.design_system.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProfileImage(
    modifier: Modifier = Modifier,
    imageUrl: String?
) {
    if (imageUrl.isNullOrBlank() || imageUrl == "null") {
        Image(
            modifier = modifier.clip(CircleShape),
            painter = painterResource(id = R.drawable.ic_profile_user),
            contentDescription = "organization_profile",
            contentScale = ContentScale.Crop
        )
    } else {
        GlideImage(
            modifier = modifier
                .clip(CircleShape),
            model = imageUrl,
            contentDescription = imageUrl,
            loading = placeholder(R.drawable.ic_profile_user),
            failure = placeholder(R.drawable.ic_profile_user),
            contentScale = ContentScale.Crop,
            transition = CrossFade,
        )
    }
}