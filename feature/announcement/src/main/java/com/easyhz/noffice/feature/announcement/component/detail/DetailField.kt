package com.easyhz.noffice.feature.announcement.component.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.skeleton.Skeleton
import com.easyhz.noffice.core.design_system.component.skeleton.SkeletonProvider
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Footer14
import com.easyhz.noffice.core.design_system.theme.Grey500
import com.easyhz.noffice.core.design_system.theme.Grey800
import com.easyhz.noffice.core.design_system.theme.SubTitle1
import com.easyhz.noffice.core.design_system.theme.White
import com.easyhz.noffice.feature.announcement.util.detail.DetailType

internal fun LazyListScope.detailField(
    modifier: Modifier = Modifier,
    detailType: DetailType,
    value: String,
    isLoading: Boolean,
    onClick: () -> Unit
) {
    item {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(White)
                .clickable(
                    enabled = detailType.hasDetail && !isLoading
                ) { onClick() }
                .screenHorizonPadding()
                .padding(vertical = 18.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(detailType.backgroundColor),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        painter = painterResource(id = detailType.iconId),
                        contentDescription = detailType.iconId.toString(),
                        tint = White
                    )
                }
                Text(
                    text = stringResource(id = detailType.stringId),
                    style = Footer14,
                    color = Grey500,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            SkeletonProvider(isLoading = isLoading, skeletonContent = {
                Skeleton(modifier = Modifier
                    .height(16.dp)
                    .fillMaxWidth(0.7f))
            }) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = value,
                        style = SubTitle1,
                        color = Grey800,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    if (detailType.hasDetail) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_chevron_right),
                            contentDescription = "more",
                            tint = detailType.backgroundColor
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun DetailFieldPrev() {
    LazyColumn {
        detailField(detailType = DetailType.DATE_TIME, value = "서울 창업 허브 어쩌fwefwefewfweefw고 저쩌고 안녀안녕" , isLoading = true) {

        }
    }
}