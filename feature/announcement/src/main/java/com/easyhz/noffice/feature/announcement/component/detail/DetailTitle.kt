package com.easyhz.noffice.feature.announcement.component.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.easyhz.noffice.core.design_system.component.skeleton.Skeleton
import com.easyhz.noffice.core.design_system.component.skeleton.SkeletonProvider
import com.easyhz.noffice.core.design_system.theme.Footer14
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.theme.Grey800
import com.easyhz.noffice.core.design_system.theme.Title1


internal fun LazyListScope.detailTitle(
    modifier: Modifier = Modifier,
    title: String,
    date: String,
    isLoading: Boolean
) {
    item {
        Column(
            modifier = modifier.padding(vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SkeletonProvider(
                isLoading = isLoading && title.isBlank(),
                skeletonContent = {
                    Skeleton(
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .height(32.dp)
                    )
                }
            ) {
                Text(
                    text = title,
                    style = Title1,
                    lineHeight = (24 * 1.4).sp,
                    color = Grey800,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            SkeletonProvider(isLoading = isLoading, skeletonContent = {
                Skeleton(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(16.dp)
                )
            }) {
                Text(
                    text = date,
                    style = Footer14,
                    color = Grey400,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}