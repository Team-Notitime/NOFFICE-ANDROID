package com.easyhz.noffice.feature.home.component.notice

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.component.banner.Banner
import com.easyhz.noffice.core.design_system.util.card.CardExceptionType
import com.easyhz.noffice.feature.home.component.common.OrganizationHeader

@Composable
fun NoticeView(
    modifier: Modifier = Modifier,
) {

    Column(modifier = modifier) {
        Banner(userName = "푸바옹", date = "금요일")
        OrganizationHeader(
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .padding(horizontal = 30.dp),
            organizationName = "CMC 15th"
        ) {

        }
        LazyRow(
            userScrollEnabled = true,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
        ) {
            exceptionItem(CardExceptionType.NO_RESULT)
            exceptionItem(CardExceptionType.ACCEPT_WAIT)
        }
    }
}