package com.easyhz.noffice.feature.announcement.component.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.component.image.OrganizationImage
import com.easyhz.noffice.core.design_system.component.skeleton.Skeleton
import com.easyhz.noffice.core.design_system.component.skeleton.SkeletonProvider
import com.easyhz.noffice.core.design_system.theme.Grey500
import com.easyhz.noffice.core.design_system.theme.Grey700
import com.easyhz.noffice.core.design_system.theme.SubBody12
import com.easyhz.noffice.core.design_system.theme.SubBody14

@Composable
internal fun OrganizationField(
    modifier: Modifier = Modifier,
    organizationName: String,
    profileImage: String,
    category: String,
    isLoading: Boolean,
) {
    SkeletonProvider(
        modifier = modifier
            .fillMaxWidth()
            .padding(6.dp),
        isLoading = isLoading,
        skeletonContent = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Skeleton(modifier = Modifier.size(36.dp).clip(CircleShape))
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Skeleton(modifier = Modifier.height(18.dp).fillMaxWidth(0.7f))
                    Skeleton(modifier = Modifier.height(14.dp).fillMaxWidth(0.5f))
                }
            }
        }
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OrganizationImage(
                modifier = Modifier.size(36.dp),
                imageUrl = profileImage
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = organizationName, style = SubBody14, color = Grey700, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(text = category, style = SubBody12, color = Grey500, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OrganizationFieldPrev() {
    OrganizationField(organizationName = "CMC 15th", profileImage = "", category = "IT와 창업" , isLoading = false)
}