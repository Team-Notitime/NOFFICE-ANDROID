package com.easyhz.noffice.feature.organization.component.management

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.image.OrganizationImage
import com.easyhz.noffice.core.design_system.extension.borderBottom
import com.easyhz.noffice.core.design_system.extension.noRippleClickable
import com.easyhz.noffice.core.design_system.theme.Blue700
import com.easyhz.noffice.core.design_system.theme.Grey200
import com.easyhz.noffice.core.design_system.theme.SemiBold12
import com.easyhz.noffice.core.design_system.theme.Title1

@Composable
internal fun ManagementHeader(
    modifier: Modifier = Modifier,
    organizationName: String,
    organizationProfileImage: String,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Column(
            modifier = modifier.noRippleClickable { onClick() },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            OrganizationImage(
                modifier = Modifier.size(86.dp),
                imageUrl = organizationProfileImage
            )
            Text(
                text = stringResource(id = R.string.organization_management_edit_image),
                style = SemiBold12,
                color = Blue700
            )
        }
        Text(
            modifier = Modifier.borderBottom(color = Grey200, width = 1.dp).padding(bottom = 8.dp),
            text = organizationName,
            style = Title1
        )
    }
}