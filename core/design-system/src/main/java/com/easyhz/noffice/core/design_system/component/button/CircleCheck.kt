package com.easyhz.noffice.core.design_system.component.button

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.R

@Composable
fun CircleCheck(
    modifier: Modifier = Modifier,
    isChecked: Boolean,
    enabled: Boolean,
    onClick: () -> Unit = { }
) {
    val checkedIconId = if(isChecked) R.drawable.ic_check_circle_on else R.drawable.ic_check_circle_off

    Crossfade(
        modifier = modifier.clickable(enabled) { onClick() },
        targetState = checkedIconId,
        label = "Check"
    ) { iconId ->
        Image(
            modifier = Modifier
                .size(20.dp),
            painter = painterResource(id = iconId),
            contentDescription = "check"
        )
    }
}