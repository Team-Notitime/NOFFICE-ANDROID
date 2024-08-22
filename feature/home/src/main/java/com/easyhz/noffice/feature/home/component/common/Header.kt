package com.easyhz.noffice.feature.home.component.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.extension.skeletonEffect
import com.easyhz.noffice.core.design_system.theme.Grey50
import com.easyhz.noffice.core.design_system.theme.Grey800
import com.easyhz.noffice.core.design_system.theme.InputDialogTitle

@Composable
internal fun OrganizationHeader(
    modifier: Modifier = Modifier,
    organizationName: String,
    onClick: () -> Unit
) {
    Box(modifier.clickable { onClick() }) {
        Row(
            modifier = Modifier
                .padding(horizontal = 14.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = organizationName, style = InputDialogTitle)
            Box(
                modifier = Modifier
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
}

@Composable
internal fun OrganizationTaskHeader(
    modifier: Modifier = Modifier,
    organizationName: String,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .height(48.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Grey50)
            .clickable { onClick() }
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = organizationName, style = InputDialogTitle)
        Box(
            modifier = Modifier
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
internal fun SkeletonOrganizationHeader(
    modifier: Modifier = Modifier,
) {

    Row(
        modifier = modifier
            .padding(horizontal = 14.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .height(20.dp)
                .fillMaxWidth(0.3f)
                .clip(RoundedCornerShape(8.dp))
                .skeletonEffect()
        )
        Box(
            modifier = Modifier
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
private fun Prev() {
    OrganizationHeader(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .padding(horizontal = 30.dp),
        organizationName = "CMC 15th"
    ) {

    }
}