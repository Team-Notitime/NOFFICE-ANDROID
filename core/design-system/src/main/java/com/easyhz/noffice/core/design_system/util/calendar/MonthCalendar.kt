package com.easyhz.noffice.core.design_system.util.calendar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.graphics.Color
import com.easyhz.noffice.core.design_system.theme.Green500
import com.easyhz.noffice.core.design_system.theme.Grey300
import com.easyhz.noffice.core.design_system.theme.Grey700
import com.easyhz.noffice.core.design_system.theme.Red
import com.easyhz.noffice.core.design_system.theme.White
import com.kizitonwose.calendar.compose.CalendarLayoutInfo
import com.kizitonwose.calendar.compose.CalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.CalendarMonth
import com.kizitonwose.calendar.core.Week
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import java.time.DayOfWeek

const val RANGE_MONTH = 500L


/**
 * 스크롤 감지하고 해당 월 반환
 *
 * @param state HorizontalCalendar 의 상태인 [CalendarState]
 * @param viewportPercent 넘어오는 정도 default : 50f
 *
 * @return 해당 주 [Week]
 */
@Composable
internal fun rememberFirstMostVisibleMonth(
    state: CalendarState,
    viewportPercent: Float = 50f,
): CalendarMonth {
    val visibleMonth = remember(state) { mutableStateOf(state.firstVisibleMonth) }
    LaunchedEffect(state) {
        snapshotFlow { state.layoutInfo.firstMostVisibleMonth(viewportPercent) }
            .distinctUntilChanged()
            .filterNotNull()
            .collect { month -> visibleMonth.value = month }
    }
    return visibleMonth.value
}


private fun CalendarLayoutInfo.firstMostVisibleMonth(viewportPercent: Float = 50f): CalendarMonth? {
    return if (visibleMonthsInfo.isEmpty()) {
        null
    } else {
        val viewportSize = (viewportEndOffset + viewportStartOffset) * viewportPercent / 100f
        visibleMonthsInfo.firstOrNull { itemInfo ->
            if (itemInfo.offset < 0) {
                itemInfo.offset + itemInfo.size >= viewportSize
            } else {
                itemInfo.size - itemInfo.offset >= viewportSize
            }
        }?.month
    }
}

internal fun getSelectionBoxColor(isSelected: Boolean): Color {
    return if (isSelected) Green500 else White
}

internal fun getTextColor(day: CalendarDay, isSelected: Boolean, enabled: Boolean): Color {
    return when {
        isSelected -> White
        !enabled -> Grey300
        day.date.dayOfWeek == DayOfWeek.SUNDAY -> Red
        else -> Grey700
    }
}