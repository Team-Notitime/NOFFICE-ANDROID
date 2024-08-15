package com.easyhz.noffice.feature.organization.util.creation

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.easyhz.noffice.core.common.util.DateFormat
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.theme.CalendarCaption1
import com.easyhz.noffice.core.design_system.theme.CalendarCaption2
import java.time.LocalDate

@Composable
internal fun rememberEndString(endDate: LocalDate?, caption: String) = remember(endDate, caption) {
    buildAnnotatedString {
        withStyle(style = CalendarCaption1.toSpanStyle()) {
            append(endDate?.let { DateFormat.fullText(it) } ?: "")
        }
        withStyle(style = CalendarCaption2.toSpanStyle()) {
            append(caption)
        }
    }
}

@StringRes
internal fun getCaption(endDate: LocalDate?): Int = when (endDate) {
    null -> R.string.organization_creation_end_date_null_caption
    else -> R.string.organization_creation_end_date_caption
}