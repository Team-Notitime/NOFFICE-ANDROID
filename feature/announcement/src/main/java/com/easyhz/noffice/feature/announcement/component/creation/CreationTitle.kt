package com.easyhz.noffice.feature.announcement.component.creation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.easyhz.noffice.core.design_system.theme.Title1

@Composable
internal fun CreationTitle(
    modifier: Modifier = Modifier,
    title: String
) {
    Box(modifier = modifier.padding(vertical = 24.dp, horizontal = 14.dp).fillMaxWidth()) {
        Text(text = title, style = Title1, lineHeight = (24 * 1.3).sp)
    }
}