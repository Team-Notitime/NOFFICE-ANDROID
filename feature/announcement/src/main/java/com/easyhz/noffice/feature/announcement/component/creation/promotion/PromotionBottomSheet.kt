package com.easyhz.noffice.feature.announcement.component.creation.promotion

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.bottomSheet.BottomSheet
import com.easyhz.noffice.core.design_system.component.button.MediumButton
import com.easyhz.noffice.core.design_system.component.image.AnnouncementImage
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Green700
import com.easyhz.noffice.core.design_system.theme.Grey800
import com.easyhz.noffice.core.design_system.theme.SemiBold16
import com.easyhz.noffice.core.design_system.theme.SemiBold18
import com.easyhz.noffice.core.design_system.theme.SubBody12
import com.easyhz.noffice.core.design_system.theme.White
import com.easyhz.noffice.core.model.image.ImagePurpose
import com.easyhz.noffice.core.model.organization.CoverImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PromotionBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    hasPromotion: Boolean,
    selectedCard: Int,
    coverImages: List<CoverImage>,
    onDismissRequest: () -> Unit,
    onClickJoinPromotion: () -> Unit,
    onClickItem: (Int) -> Unit,
    onClickButton: () -> Unit
) {
    BottomSheet(
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        containerColor = White,
        onDismissRequest = onDismissRequest,
    ) {
        Column(
            modifier = modifier
                .padding(vertical = 16.dp)
                .screenHorizonPadding(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Card(
                cardTitle = stringResource(id = R.string.announcement_creation_option_promotion_basic_title),
                selectedCard = coverImages[selectedCard],
                hasPromotion = true,
                cardItems = coverImages.filter { it.type != ImagePurpose.PROMOTION_COVER },
                onClickJoinPromotion = onClickJoinPromotion
            ) { onClickItem(coverImages.indexOf(it)) }
            Card(
                cardTitle = stringResource(id = R.string.announcement_creation_option_promotion_promotion_title),
                selectedCard = coverImages[selectedCard],
                hasPromotion = hasPromotion,
                cardItems = coverImages.filter { it.type == ImagePurpose.PROMOTION_COVER },
                onClickJoinPromotion = onClickJoinPromotion
            ) { onClickItem(coverImages.indexOf(it)) }
            MediumButton(
                modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                text = stringResource(id = R.string.announcement_creation_option_promotion_select_button),
                enabled = true,
                textStyle = SemiBold16,
                onClick = onClickButton
            )
        }
    }
}

@Composable
private fun Card(
    modifier: Modifier = Modifier,
    cardTitle: String,
    selectedCard: CoverImage,
    hasPromotion: Boolean,
    cardItems: List<CoverImage>,
    onClickJoinPromotion: () -> Unit,
    onClick: (CoverImage) -> Unit,
) {
    Column {
        Text(
            text = cardTitle, style = SemiBold18, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 12.dp)
        )
        if (!hasPromotion) {
            Box(modifier = Modifier.clip(RoundedCornerShape(8.dp))) {
                Image(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .blur(32.dp),
                    painter = painterResource(id = R.drawable.ic_blur), contentDescription = "blur"
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.announcement_creation_option_promotion_promotion_text),
                        style = SubBody12,
                        color = Green700,
                        textAlign = TextAlign.Center
                    )
                    MediumButton(
                        modifier = Modifier.widthIn(min = 220.dp),
                        text = stringResource(id = R.string.announcement_creation_option_promotion_promotion_button),
                        onClick = onClickJoinPromotion
                    )
                }
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(cardItems) { cardImage ->
                    Box(
                        modifier = modifier
                            .weight(1f)
                            .clickable { onClick(cardImage) }
                            .then(
                                Modifier
                                    .border(2.dp, Grey800, RoundedCornerShape(8.dp))
                                    .takeIf { selectedCard == cardImage } ?: Modifier
                            )
                            .clip(RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        AnnouncementImage(
                            modifier = Modifier
                                .heightIn(max = 100.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            imageUrl = cardImage.url
                        )
                        androidx.compose.animation.AnimatedVisibility(
                            visible = selectedCard == cardImage,
                            modifier = Modifier.align(Alignment.BottomEnd).padding(bottom = 8.dp, end = 8.dp),
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_check_circle_grey800),
                                contentDescription = "check"
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun CardPrev() {
    Card(
        cardTitle = stringResource(id = R.string.announcement_creation_option_promotion_promotion_title),
        selectedCard = CoverImage(1, ImagePurpose.PROMOTION_COVER, ""),
        hasPromotion = true,
        cardItems = listOf(
            CoverImage(1, ImagePurpose.PROMOTION_COVER, ""),
            CoverImage(2, ImagePurpose.PROMOTION_COVER, ""),
            CoverImage(3, ImagePurpose.PROMOTION_COVER, ""),
            CoverImage(4, ImagePurpose.PROMOTION_COVER, "")
        ),
        onClickJoinPromotion = { }
    ) { }
}