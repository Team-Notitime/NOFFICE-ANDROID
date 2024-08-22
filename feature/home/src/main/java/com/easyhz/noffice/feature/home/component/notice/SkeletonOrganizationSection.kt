package com.easyhz.noffice.feature.home.component.notice

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.component.card.SkeletonItemCard
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.feature.home.component.common.SkeletonOrganizationHeader

@Composable
internal fun SkeletonOrganizationSection(
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        SkeletonOrganizationHeader(
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .screenHorizonPadding()
                .padding(vertical = 8.dp),
        )
        LazyRow(
            userScrollEnabled = false,
            modifier = Modifier.padding(start = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(2) {
                SkeletonItemCard()
            }
        }
    }
}