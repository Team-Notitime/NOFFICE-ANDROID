package com.easyhz.noffice.core.design_system.component.timePicker

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.common.util.TimeFormat
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.theme.Body24
import com.easyhz.noffice.core.design_system.theme.Grey500
import com.easyhz.noffice.core.design_system.util.timePicker.TimePickerType
import com.easyhz.noffice.core.design_system.util.timePicker.isAmMap
import java.time.LocalTime

@Composable
fun NofficeTimePicker(
    modifier: Modifier = Modifier,
    initialTime: LocalTime?,
    onValueChange: (Int, Int, Boolean) -> Unit
) {
    val (hour, setHour) = rememberSaveable { mutableStateOf(12) }
    val (minute, setMinute) = rememberSaveable { mutableStateOf(0) }
    val (isAM, setIsAM) = rememberSaveable { mutableStateOf(true) }

    LaunchedEffect(initialTime) {
        if (initialTime != null) {
            val (h, m, am) = TimeFormat.getHourMinuteIsAm(initialTime)
            setHour(h)
            setMinute(m)
            setIsAM(am)
        }
    }
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Item(value = hour, range = TimePickerType.HOUR.range) {
                setHour(it)
                onValueChange(it, minute, isAM)
            }
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = ":",
                style = Body24,
                maxLines = 1,
                softWrap = false
            )
            Item(value = minute, range = TimePickerType.MINUTE.range) {
                setMinute(it)
                onValueChange(hour, it, isAM)
            }
        }
        isAmItem(value = isAM) {
            setIsAM(it)
            onValueChange(hour, minute, it)
        }

    }
}

@Composable
private fun Item(
    modifier: Modifier = Modifier,
    value: Int,
    range: IntRange,
    onValueChange: (Int) -> Unit
) {
    var selectedValue by rememberSaveable(value) { mutableStateOf(value) }

    Column(
        modifier = modifier.widthIn(min = 36.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .clickable {
                    selectedValue =
                        if (selectedValue < range.last) selectedValue + 1 else range.first
                    onValueChange(selectedValue)
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_chevron_up),
                contentDescription = "up",
                tint = Grey500
            )
        }
        AnimatedCounter(
            count = selectedValue.toTwoDigitString()
        )
        Box(
            modifier = Modifier
                .size(32.dp)
                .clickable {
                    selectedValue =
                        if (selectedValue > range.first) selectedValue - 1 else range.last
                    onValueChange(selectedValue)
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_chevron_down),
                contentDescription = "down",
                tint = Grey500
            )
        }
    }
}

@Composable
private fun isAmItem(
    modifier: Modifier = Modifier,
    value: Boolean,
    onValueChange: (Boolean) -> Unit
) {
    var selectedValue by rememberSaveable(value) { mutableStateOf(value) }

    Column(
        modifier = modifier.widthIn(min = 36.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .clickable {
                    selectedValue = !value
                    onValueChange(selectedValue)
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_chevron_up),
                contentDescription = "up",
                tint = Grey500
            )
        }
        AnimatedCounter(
            count = isAmMap[value] ?: "AM"
        )
        Box(
            modifier = Modifier
                .size(32.dp)
                .clickable {
                    selectedValue = !value
                    onValueChange(selectedValue)
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_chevron_down),
                contentDescription = "down",
                tint = Grey500
            )
        }
    }
}

@Composable
private fun AnimatedCounter(
    count: String,
    modifier: Modifier = Modifier,
) {
    AnimatedContent(
        modifier = modifier,
        targetState = count,
        transitionSpec = {
            if (targetState < initialState) {
                slideInVertically { -it } togetherWith slideOutVertically { it }
            } else {
                slideInVertically { it } togetherWith slideOutVertically { -it }
            }
        },
        label = "value"
    ) { value ->
        Text(
            text = value,
            style = Body24,
            maxLines = 1,
            softWrap = false
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ItemPrev() {
    Item(value = 12, range = 1..12) {

    }
}

private fun Int.toTwoDigitString() = if (this < 10) "0$this" else this.toString()
