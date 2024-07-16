package com.easyhz.noffice.feature.home.component.notice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.component.card.ExceptionCard
import com.easyhz.noffice.core.design_system.theme.Grey50
import com.easyhz.noffice.core.design_system.util.card.CardExceptionType

fun LazyListScope.noResultItem() {
    item {
        ExceptionCard(type = CardExceptionType.NO_RESULT)
    }
    item {
        Box(modifier = Modifier
            .height(296.dp)
            .width(283.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Grey50)) { }
    }
}