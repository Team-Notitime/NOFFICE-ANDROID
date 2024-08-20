package com.easyhz.noffice.feature.announcement.component.creation.promotion

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.theme.Grey800
import com.easyhz.noffice.feature.announcement.contract.creation.promotion.CardImage

@Composable
internal fun PromotionCard(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    cardImage: CardImage,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .width(92.dp)
            .height(52.dp)
            .clickable { onClick() }
            .then(
                Modifier
                    .border(2.dp, Grey800, RoundedCornerShape(8.dp))
                    .takeIf { isSelected } ?: Modifier
            )
            .clip(RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .height(184.dp)
                .clip(RoundedCornerShape(8.dp)),
            painter = painterResource(id = cardImage.imageId),
            contentDescription = cardImage.imageId.toString(),
            contentScale = ContentScale.Crop,
        )
    }
}