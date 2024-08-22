package com.easyhz.noffice.core.design_system.component.card

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.image.AnnouncementImage
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.extension.skeletonEffect
import com.easyhz.noffice.core.design_system.theme.CardExceptionSubTitle
import com.easyhz.noffice.core.design_system.theme.CardExceptionTitle
import com.easyhz.noffice.core.design_system.theme.Grey200
import com.easyhz.noffice.core.design_system.theme.Grey600
import com.easyhz.noffice.core.design_system.theme.SemiBold14
import com.easyhz.noffice.core.design_system.theme.semiBold
import com.easyhz.noffice.core.design_system.util.card.CardDetailInfo
import com.easyhz.noffice.core.design_system.util.card.CardExceptionType

@Composable
fun ItemCard(
    modifier: Modifier = Modifier,
    title: String,
    imageUrl: String,
    detailItems: List<CardDetailInfo>,
    onClick: () -> Unit
) {
    Column(modifier = modifier
        .width(283.dp)
        .height(296.dp)
        .clickable { onClick() }
        .clip(RoundedCornerShape(16.dp))
        .border(1.dp, color = Grey200, RoundedCornerShape(16.dp))
    ) {
        AnnouncementImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp),
            imageUrl = imageUrl
        )
        Box(
            modifier = Modifier
                .screenHorizonPadding()
                .padding(start = 4.dp)
                .fillMaxWidth()
                .heightIn(min = 52.dp)
        ) {
            Text(
                modifier = Modifier.align(Alignment.CenterStart),
                text = title,
                style = semiBold(18),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Spacer(
            modifier = Modifier
                .screenHorizonPadding()
                .height(1.dp)
                .fillMaxWidth()
                .border(1.dp, color = Grey200)
        )
        DetailFields(
            modifier = Modifier
                .screenHorizonPadding()
                .padding(top = 15.dp),
            items = detailItems
        )
    }
}

@Composable
fun SkeletonItemCard(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(283.dp)
            .height(296.dp)
            .clip(RoundedCornerShape(16.dp))
            .border(1.dp, color = Grey200, RoundedCornerShape(16.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .skeletonEffect(),
        )
        /* 타이틀 */
        Box(
            modifier = Modifier
                .screenHorizonPadding()
                .padding(start = 4.dp)
                .fillMaxWidth()
                .heightIn(min = 52.dp)
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .height(20.dp)
                    .fillMaxWidth(0.7f)
                    .clip(RoundedCornerShape(8.dp))
                    .skeletonEffect(),
            )
        }
        Spacer(
            modifier = Modifier
                .screenHorizonPadding()
                .height(1.dp)
                .fillMaxWidth()
                .border(1.dp, color = Grey200)
        )
        /* DetailFields */
        Column(
            modifier = modifier
                .screenHorizonPadding()
                .padding(top = 15.dp, start = 4.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(18.dp)
                    .fillMaxWidth(0.5f)
                    .clip(RoundedCornerShape(8.dp))
                    .skeletonEffect()
            )
            Box(
                modifier = Modifier
                    .height(18.dp)
                    .fillMaxWidth(0.7f)
                    .clip(RoundedCornerShape(8.dp))
                    .skeletonEffect()
            )
        }
    }
}

@Composable
private fun DetailFields(
    modifier: Modifier = Modifier,
    items: List<CardDetailInfo>
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items.forEach { item ->
            DetailField(
                iconId = item.iconId, text = item.text, description = item.description
            )
        }
    }
}

@Composable
private fun DetailField(
    modifier: Modifier = Modifier,
    @DrawableRes iconId: Int,
    text: String,
    description: String
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            modifier = Modifier.size(18.dp),
            painter = painterResource(id = iconId),
            contentDescription = description,
            tint = Grey200
        )
        Text(
            text = text,
            style = SemiBold14,
            color = Grey600,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun ExceptionCard(
    modifier: Modifier = Modifier,
    type: CardExceptionType
) {
    Column(
        modifier = modifier
            .width(283.dp)
            .height(296.dp)
            .clip(RoundedCornerShape(16.dp))
            .border(1.dp, color = Grey200, RoundedCornerShape(16.dp)),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Image(
                modifier = Modifier.align(Alignment.BottomCenter),
                painter = painterResource(id = type.resId),
                contentScale = ContentScale.Crop,
                contentDescription = type.name
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.8f),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier,
                text = stringResource(id = type.titleStringId),
                textAlign = TextAlign.Center,
                style = CardExceptionTitle,
            )
            Text(
                modifier = Modifier,
                text = stringResource(id = type.subTitleStringId),
                textAlign = TextAlign.Center,
                style = CardExceptionSubTitle,
            )
        }
    }
}

@Preview(showBackground = true, name = "card")
@Composable
private fun ItemCardPrev() {
    ItemCard(
        title = "5차 세션: 최종 팀빌딩",
        imageUrl = "",
        detailItems = listOf(
            CardDetailInfo(
                iconId = R.drawable.ic_calendar,
                text = "2024.06.29(토) 14:00",
                description = "date"
            ),
            CardDetailInfo(
                iconId = R.drawable.ic_mappin,
                text = "ZEP",
                description = "place"
            )
        )
    ) {

    }
}

@Preview(showBackground = true, name = "loading")
@Composable
private fun ExceptionCardPrev() {
    ExceptionCard(type = CardExceptionType.ACCEPT_WAIT)
}

@Preview(showBackground = true, name = "none")
@Composable
private fun ExceptionNoneCardPrev() {
    ExceptionCard(type = CardExceptionType.NO_RESULT)
}