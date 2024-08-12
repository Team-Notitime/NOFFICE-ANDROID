package com.easyhz.noffice.feature.home.component.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.easyhz.noffice.core.design_system.theme.Grey300
import com.easyhz.noffice.core.design_system.theme.SubBody14
import com.easyhz.noffice.core.design_system.theme.Title3
import com.easyhz.noffice.core.design_system.theme.White
import com.easyhz.noffice.feature.home.util.OnboardingStep

@Composable
internal fun OnboardingItem(
    modifier: Modifier = Modifier,
    item: OnboardingStep
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = stringResource(id = item.titleId),
                style = Title3,
                color = White,
                lineHeight = (22 * 1.3).sp
            )
            Text(
                modifier = Modifier.heightIn(min= 56.dp),
                text = stringResource(id = item.contentId),
                style = SubBody14,
                color = Grey300,
                lineHeight = (14 * 1.4).sp
            )
        }
        Image(
            modifier = Modifier.fillMaxWidth().weight(1f),
            painter = painterResource(id = item.imageId),
            contentDescription = item.name
        )
    }
}