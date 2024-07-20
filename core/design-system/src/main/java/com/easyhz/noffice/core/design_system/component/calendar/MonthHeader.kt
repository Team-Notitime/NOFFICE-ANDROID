package com.easyhz.noffice.core.design_system.component.calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.extension.noRippleClickable
import com.easyhz.noffice.core.design_system.theme.Grey500
import com.easyhz.noffice.core.design_system.theme.InputDialogTitle

@Composable
internal fun MonthHeader(
    modifier: Modifier = Modifier,
    title: String,
    onClickBefore: () -> Unit,
    onClickNext: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Box(modifier = Modifier.size(32.dp).noRippleClickable { onClickBefore() }) {
            Icon(
                modifier = Modifier.align(Alignment.CenterStart).size(24.dp),
                painter = painterResource(id = R.drawable.ic_chevron_left),
                contentDescription = "before",
                tint = Grey500
            )
        }
        Text(
            text = title,
            style = InputDialogTitle
        )
        Box(modifier = Modifier.size(32.dp).noRippleClickable { onClickNext() }) {
            Icon(
                modifier = Modifier.align(Alignment.CenterEnd).size(24.dp),
                painter = painterResource(id = R.drawable.ic_chevron_right),
                contentDescription = "next",
                tint = Grey500
            )
        }
    }
}