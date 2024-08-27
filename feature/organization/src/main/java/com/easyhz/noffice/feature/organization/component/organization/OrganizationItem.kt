package com.easyhz.noffice.feature.organization.component.organization

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.image.OrganizationImage
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.extension.skeletonEffect
import com.easyhz.noffice.core.design_system.theme.Grey800
import com.easyhz.noffice.core.design_system.theme.SemiBold16

@Composable
internal fun OrganizationItem(
    modifier: Modifier = Modifier,
    organizationName: String,
    imageUrl: String?,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .padding(vertical = 8.dp)
            .height(64.dp)
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OrganizationImage(
            modifier = Modifier.size(48.dp),
            imageUrl = imageUrl
        )
        Text(
            modifier = Modifier.weight(1f),
            text = organizationName,
            style = SemiBold16,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Box(modifier = Modifier
            .sizeIn(minWidth = 32.dp, minHeight = 32.dp)
            .fillMaxHeight()
        ) {
            Icon(
                modifier = Modifier.align(Alignment.CenterEnd),
                painter = painterResource(id = R.drawable.ic_chevron_right),
                contentDescription = "rightArrow",
                tint = Grey800
            )
        }
    }
}

@Composable
internal fun SkeletonOrganizationItem(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .padding(vertical = 8.dp)
            .height(64.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(48.dp)
                .skeletonEffect(),
        )
        Box(
            modifier = Modifier
                .height(24.dp)
                .fillMaxWidth(0.3f)
                .skeletonEffect(),
        )
        Box(modifier = Modifier.weight(1f))
        Box(modifier = Modifier
            .sizeIn(minWidth = 32.dp, minHeight = 32.dp)
            .fillMaxHeight()
        ) {
            Icon(
                modifier = Modifier.align(Alignment.CenterEnd),
                painter = painterResource(id = R.drawable.ic_chevron_right),
                contentDescription = "rightArrow",
                tint = Grey800
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OrganizationItemPrev() {
    OrganizationItem(
        modifier = Modifier
            .fillMaxWidth()
            .screenHorizonPadding(),
        organizationName = "그룹 이름",
        imageUrl = ""
    ) {

    }
}