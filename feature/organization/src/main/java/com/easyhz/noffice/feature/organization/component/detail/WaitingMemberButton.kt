package com.easyhz.noffice.feature.organization.component.detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.theme.Green600
import com.easyhz.noffice.core.design_system.theme.SemiBold16

@Composable
internal fun WaitingMemberButton(
    modifier: Modifier = Modifier,
    hasWaitingMember: Boolean,
    onClick: () -> Unit
) {
    AnimatedVisibility(
        visible = hasWaitingMember,
        enter = scaleIn(
            initialScale = 0.3f,
            animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy,stiffness = Spring.StiffnessMediumLow )
        ),
        exit = scaleOut(
            animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy,stiffness = Spring.StiffnessMediumLow )
        )
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(44.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Green600.copy(alpha = 0.04f))
                .border(width = 1.dp, color = Green600, shape = RoundedCornerShape(8.dp))
                .clickable { onClick() }
        ) {
            Row(
                modifier = Modifier.align(Alignment.Center),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Image(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(id = R.drawable.ic_load),
                    contentDescription = "load"
                )
                Text(
                    text = stringResource(id = R.string.organization_member_waiting),
                    style = SemiBold16,
                    color = Green600,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}