package com.easyhz.noffice.feature.announcement.component.creation.promotion

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
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
                .then(
                    Modifier
                        .blur(16.dp)
                        .takeIf { cardImage.isPromotion } ?: Modifier
                )
                .height(184.dp)
                .clip(RoundedCornerShape(8.dp)),
            painter = painterResource(id = cardImage.imageId),
            contentDescription = cardImage.imageId.toString(),
            contentScale = ContentScale.Crop,
        )
        if (cardImage.isPromotion) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = com.easyhz.noffice.core.design_system.R.drawable.ic_lock),
                    contentDescription = "lock",
                    tint = Grey800
                )
            }
        }
    }
}

@Preview
@Composable
private fun PromotionCardPrev() {
    PromotionCard(isSelected = true, cardImage = CardImage.CARD3) {

    }
}