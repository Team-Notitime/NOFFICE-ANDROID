package com.easyhz.noffice.core.design_system.component.calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.theme.Grey700
import com.easyhz.noffice.core.design_system.theme.SubBody14
import com.easyhz.noffice.core.design_system.util.calendar.RANGE_MONTH
import com.easyhz.noffice.core.design_system.util.calendar.displayText
import com.easyhz.noffice.core.design_system.util.calendar.rememberFirstMostVisibleMonth
import com.kizitonwose.calendar.compose.CalendarState
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.OutDateStyle
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import com.kizitonwose.calendar.core.yearMonth
import kotlinx.coroutines.launch
import java.time.LocalDate

@Composable
fun MonthCalendarView(
    modifier: Modifier = Modifier,
    selection: LocalDate,
    calendarPadding: Dp,
    onChangeDate: (LocalDate) -> Unit
) {
    val scope = rememberCoroutineScope()
    val today = remember { LocalDate.now() }
    val startMonth = remember { today.yearMonth }
    val endMonth = remember { today.yearMonth.plusMonths(RANGE_MONTH) }
    val monthState = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstDayOfWeek = firstDayOfWeekFromLocale(),
        outDateStyle = OutDateStyle.EndOfRow,
    )
    val title = rememberFirstMostVisibleMonth(state = monthState)

    LaunchedEffect(selection) {
        if(title.yearMonth == selection.yearMonth) return@LaunchedEffect
        monthState.animateScrollToMonth(selection.yearMonth)
    }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        MonthHeader(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            title = title.yearMonth.displayText(),
            onClickBefore = {
                scope.launch {
                    monthState.animateScrollToMonth(title.yearMonth.minusMonths(1))
                }
            },
            onClickNext = {
                scope.launch {
                    monthState.animateScrollToMonth(title.yearMonth.plusMonths(1))
                }
            }
        )
        MonthCalendarContent(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = calendarPadding),
            monthState = monthState,
            selection = selection,
            today = today,
            onChangeDate = onChangeDate
        )
    }
}

@Composable
fun MonthCalendarContent(
    modifier: Modifier = Modifier,
    monthState: CalendarState,
    selection: LocalDate,
    today: LocalDate,
    onChangeDate: (LocalDate) -> Unit
) {
    HorizontalCalendar(
        modifier = modifier,
        state = monthState,
        monthHeader = { CalendarHeader() },
        dayContent = {day ->
            Day(
                day = day,
                isSelected = selection == day.date,
                today = today,
            ) { clickedDay ->
                onChangeDate(clickedDay)
            }
        },
    )
}

@Stable
@Composable
internal fun CalendarHeader() {
    val daysOfWeek = remember { daysOfWeek() }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
    ) {
        for (dayOfWeek in daysOfWeek) {
            Text(
                modifier = Modifier.weight(1f),
                text = dayOfWeek.displayText(),
                style = SubBody14,
                color = Grey700,
                textAlign = TextAlign.Center
            )
        }
    }
}