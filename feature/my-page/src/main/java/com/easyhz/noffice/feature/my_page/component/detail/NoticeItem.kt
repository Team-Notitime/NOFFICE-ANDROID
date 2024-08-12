package com.easyhz.noffice.feature.my_page.component.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.theme.Grey600
import com.easyhz.noffice.core.design_system.theme.SemiBold16
import com.easyhz.noffice.core.design_system.theme.SubBody12
import com.easyhz.noffice.core.design_system.theme.SubBody14
import com.easyhz.noffice.core.design_system.theme.White
import com.easyhz.noffice.core.model.notice.Notice

@Composable
internal fun NoticeItem(
    modifier: Modifier = Modifier,
    item: Notice,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(White)
            .clickable { onClick() }
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = item.title, style = SemiBold16, maxLines = 1, overflow = TextOverflow.Ellipsis)
        Text(
            text = item.content,
            style = SubBody14,
            color = Grey600,
            lineHeight = (14 * 1.4).sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = item.date,
            style = SubBody12,
            color = Grey400,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}