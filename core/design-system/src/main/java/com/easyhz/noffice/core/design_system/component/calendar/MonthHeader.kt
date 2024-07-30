package com.easyhz.noffice.core.design_system.component.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.extension.noRippleClickable
import com.easyhz.noffice.core.design_system.theme.Grey500
import com.easyhz.noffice.core.design_system.theme.InputDialogTitle
import com.easyhz.noffice.core.design_system.util.interaction.useInteractionWithColor

@Composable
internal fun MonthHeader(
    modifier: Modifier = Modifier,
    title: String,
    onClickBefore: () -> Unit,
    onClickNext: () -> Unit
) {
    val (beforeInteractionSource, beforeScale, beforeColor) = useInteractionWithColor()
    val (nextInteractionSource, nextScale, nextColor) = useInteractionWithColor()
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Box(modifier = Modifier
            .size(32.dp)
            .scale(beforeScale)
            .clip(RoundedCornerShape(8.dp))
            .background(beforeColor)
            .noRippleClickable(beforeInteractionSource) { onClickBefore() }) {
            Icon(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(24.dp),
                painter = painterResource(id = R.drawable.ic_chevron_left),
                contentDescription = "before",
                tint = Grey500
            )
        }
        Text(
            modifier = Modifier.padding(horizontal = 4.dp).widthIn(min = 76.dp),
            text = title,
            style = InputDialogTitle,
            textAlign = TextAlign.Center
        )
        Box(modifier = Modifier
            .size(32.dp)
            .scale(nextScale)
            .clip(RoundedCornerShape(8.dp))
            .background(nextColor)
            .noRippleClickable(nextInteractionSource) { onClickNext() }) {
            Icon(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(24.dp),
                painter = painterResource(id = R.drawable.ic_chevron_right),
                contentDescription = "next",
                tint = Grey500
            )
        }
    }
}