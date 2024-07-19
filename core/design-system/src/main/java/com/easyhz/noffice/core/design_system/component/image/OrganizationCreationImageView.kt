package com.easyhz.noffice.core.design_system.component.image

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.bumptech.glide.integration.compose.CrossFade
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.easyhz.noffice.core.design_system.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun OrganizationCreationImageView(
    modifier: Modifier = Modifier,
    image: Uri
) {
    GlideImage(
        modifier = modifier,
        model = image,
        contentDescription = image.toString(),
        loading = placeholder(R.drawable.ic_profile_group),
        failure = placeholder(R.drawable.ic_profile_group),
        contentScale = ContentScale.Crop,
        transition = CrossFade,
    )
}