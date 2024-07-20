package com.easyhz.noffice.core.design_system.component.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.extension.noRippleClickable
import com.easyhz.noffice.core.design_system.theme.SemiBold14
import com.easyhz.noffice.core.design_system.util.calendar.dateFormatter
import com.easyhz.noffice.core.design_system.util.calendar.getSelectionBoxColor
import com.easyhz.noffice.core.design_system.util.calendar.getTextColor
import com.kizitonwose.calendar.core.CalendarDay
import java.time.LocalDate

@Composable
internal fun Day(
    modifier: Modifier = Modifier,
    day: CalendarDay,
    today: LocalDate,
    isSelected: Boolean,
    onClick: (LocalDate) -> Unit
) {
    val enabled = day.date >= today
    val selectionBoxColor = getSelectionBoxColor(isSelected)
    val textColor = getTextColor(day, isSelected, enabled)
    Box(
        modifier = modifier
            .fillMaxWidth()
            .noRippleClickable(enabled = enabled) { onClick(day.date) },
        contentAlignment = Alignment.TopCenter,
    ) {
        Box(modifier = Modifier.padding(bottom = 6.dp)
            .size(40.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(selectionBoxColor),
        ) {
            Text(
                modifier = Modifier.align(Alignment.TopCenter).padding(top = 4.dp),
                text = dateFormatter.format(day.date),
                style = SemiBold14,
                textAlign = TextAlign.Center,
                color = textColor
            )
        }
    }
}