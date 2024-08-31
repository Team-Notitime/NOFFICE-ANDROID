package com.easyhz.noffice.feature.announcement.component.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.easyhz.noffice.core.design_system.component.skeleton.Skeleton
import com.easyhz.noffice.core.design_system.component.skeleton.SkeletonProvider
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Grey800
import com.easyhz.noffice.core.design_system.theme.SubBody14
import com.easyhz.noffice.core.design_system.theme.White

internal fun LazyListScope.contentField(
    modifier: Modifier = Modifier,
    content: String,
    isLoading: Boolean,
) {
    item {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(White)
                .screenHorizonPadding()
                .padding(vertical = 14.dp),
        ) {
            SkeletonProvider(isLoading = isLoading,
                skeletonContent = {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Skeleton(modifier = Modifier.height(14.dp).fillMaxWidth())
                        Skeleton(modifier = Modifier.height(14.dp).fillMaxWidth(0.3f))
                        Skeleton(modifier = Modifier.height(14.dp).fillMaxWidth(0.7f))
                    }
                }
            ) {
                Text(text = content, style = SubBody14, lineHeight = (14 * 1.8).sp, color = Grey800, textAlign = TextAlign.Start)
            }
        }
    }
}