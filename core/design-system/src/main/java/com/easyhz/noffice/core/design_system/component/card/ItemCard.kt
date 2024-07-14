package com.easyhz.noffice.core.design_system.component.card

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import com.easyhz.noffice.core.design_system.extension.noRippleClickable
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.CardExceptionTitle
import com.easyhz.noffice.core.design_system.theme.Grey200
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.theme.Grey600
import com.easyhz.noffice.core.design_system.theme.SemiBold14
import com.easyhz.noffice.core.design_system.theme.semiBold
import com.easyhz.noffice.core.design_system.util.card.CardDetailInfo
import com.easyhz.noffice.core.design_system.util.card.CardExceptionType

@Composable
fun ItemCard(
    modifier: Modifier = Modifier,
    title: String,
    detailItems: List<CardDetailInfo>,
    onClick: () -> Unit
) {
    Column(modifier = modifier
        .width(283.dp)
        .height(296.dp)
        .clip(RoundedCornerShape(16.dp))
        .border(1.dp, color = Grey200, RoundedCornerShape(16.dp))
        .noRippleClickable { onClick() }
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.img_illust_1),
            contentScale = ContentScale.Crop,
            contentDescription = "image"
        )
        Box(modifier = Modifier
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
        Spacer(modifier = Modifier
            .screenHorizonPadding()
            .height(1.dp)
            .fillMaxWidth()
            .border(1.dp, color = Grey200))
        DetailFields(
            modifier = Modifier
                .screenHorizonPadding()
                .padding(top = 15.dp),
            items = detailItems
        )
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
    Column(modifier = modifier
        .width(283.dp)
        .height(296.dp)
        .clip(RoundedCornerShape(16.dp))
        .border(1.dp, color = Grey200, RoundedCornerShape(16.dp))
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(0.9f)
            .padding(bottom = 10.dp)) {
            Text(
                modifier = Modifier
                    .align(Alignment.BottomCenter),
                text = stringResource(id = type.stringId),
                textAlign = TextAlign.Center,
                style = CardExceptionTitle,
                color = Grey400
            )
        }
        Box(modifier = Modifier.fillMaxWidth().weight(1f)) {
            Image(
                modifier = Modifier.align(Alignment.TopCenter),
                painter = painterResource(id = type.resId),
                contentScale = ContentScale.Crop,
                contentDescription = type.name
            )
        }
    }
}

@Preview(showBackground = true, name = "card")
@Composable
private fun ItemCardPrev() {
    ItemCard(
        title = "5차 세션: 최종 팀빌딩",
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