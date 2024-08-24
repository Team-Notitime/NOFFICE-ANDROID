package com.easyhz.noffice.feature.organization.component.detail

import androidx.annotation.DrawableRes
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.extension.skeletonEffect
import com.easyhz.noffice.core.design_system.theme.Caption12
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.theme.Grey600
import com.easyhz.noffice.core.design_system.theme.SemiBold14
import com.easyhz.noffice.core.design_system.theme.SemiBold18
import com.easyhz.noffice.core.design_system.theme.SubBody14
import com.easyhz.noffice.core.design_system.theme.White
import com.easyhz.noffice.core.design_system.theme.Yellow100
import com.easyhz.noffice.core.design_system.theme.Yellow500
import com.easyhz.noffice.core.design_system.theme.Yellow900
import com.easyhz.noffice.core.model.organization.announcement.OrganizationAnnouncement
import com.easyhz.noffice.feature.organization.util.detail.Options

@Composable
internal fun AnnouncementCard(
    modifier: Modifier = Modifier,
    announcement: OrganizationAnnouncement,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(White)
            .clickable { onClick() }
            .padding(vertical = 16.dp, horizontal = 12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = announcement.title,
            style = SemiBold18,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            announcement.endAt.takeIf { !it.isNullOrBlank() }?.run {
                Badge(modifier = Modifier, string = this, iconId = Options.DATE.iconId)
            }

            announcement.place.takeIf { !it.isNullOrBlank() }?.run {
                Badge(
                    modifier = Modifier.weight(1f, false),
                    string = this,
                    iconId = Options.PLACE.iconId
                )
            }

            announcement.taskSize?.takeIf { it < 1 }?.run {
                Badge(
                    modifier = Modifier,
                    string = stringResource(
                        id = R.string.organization_detail_badge_number_of_badges,
                        this
                    ),
                    iconId = Options.TASK.iconId
                )
            }
        }
        Text(
            text = announcement.content.replace("\n", " "),
            style = SubBody14,
            color = Grey600,
            maxLines = 2,
            lineHeight = (14 * 1.4).sp,
            textAlign = TextAlign.Start,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = announcement.createdAt,
            style = Caption12,
            color = Grey400,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun Badge(
    modifier: Modifier = Modifier,
    string: String,
    @DrawableRes iconId: Int
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Yellow100)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            modifier = Modifier.size(12.dp),
            painter = painterResource(id = iconId),
            contentDescription = iconId.toString(),
            tint = Yellow500
        )
        Text(
            text = string,
            style = SemiBold14,
            color = Yellow900,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
internal fun SkeletonCard(
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(White)
            .padding(vertical = 16.dp, horizontal = 12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(20.dp)
                .skeletonEffect()
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.2f)
                    .height(20.dp)
                    .skeletonEffect()
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .height(20.dp)
                    .skeletonEffect()
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(20.dp)
                .skeletonEffect()
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(20.dp)
                .skeletonEffect()
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(0.3f)
                .height(20.dp)
                .skeletonEffect()
        )
    }
}