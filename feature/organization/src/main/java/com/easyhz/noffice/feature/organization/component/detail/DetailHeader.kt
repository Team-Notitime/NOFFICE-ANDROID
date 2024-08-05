package com.easyhz.noffice.feature.organization.component.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.component.chip.NofficeChip
import com.easyhz.noffice.core.design_system.component.image.OrganizationImage
import com.easyhz.noffice.core.design_system.component.skeleton.SkeletonProvider
import com.easyhz.noffice.core.design_system.extension.skeletonEffect
import com.easyhz.noffice.core.design_system.theme.Title1
import com.easyhz.noffice.core.design_system.util.chip.ChipState
import com.easyhz.noffice.core.design_system.util.chip.ChipStyles
import com.easyhz.noffice.core.model.organization.OrganizationInformation
import com.easyhz.noffice.feature.organization.util.detail.OrganizationChipStyle

@Composable
internal fun DetailHeader(
    modifier: Modifier = Modifier,
    organizationInformation: OrganizationInformation,
    isLoading: Boolean
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OrganizationImage(
            modifier = Modifier.size(86.dp),
            imageUrl = organizationInformation.profileImageUrl
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = organizationInformation.name,
                style = Title1
            )
            SkeletonProvider(
                isLoading = isLoading,
                skeletonContent = {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        repeat(2) {
                            Box(modifier = Modifier
                                .width((32 + it * 4).dp)
                                .height(24.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .skeletonEffect())
                        }
                    }
                }
            ) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items(organizationInformation.category) {
                        NofficeChip(
                            text = it,
                            selectState = ChipState.Picked,
                            chipStyles = ChipStyles(
                                unSelected = OrganizationChipStyle,
                                selected = OrganizationChipStyle,
                                picked = OrganizationChipStyle
                            ),
                            roundedCornerShape = RoundedCornerShape(4.dp)
                        ) { _, _ -> }
                    }
                }
            }
        }
    }
}