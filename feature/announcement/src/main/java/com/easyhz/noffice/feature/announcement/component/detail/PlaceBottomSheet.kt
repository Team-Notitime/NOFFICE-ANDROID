package com.easyhz.noffice.feature.announcement.component.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.extension.borderBottom
import com.easyhz.noffice.core.design_system.extension.noRippleClickable
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.theme.Grey50
import com.easyhz.noffice.core.design_system.theme.Grey700
import com.easyhz.noffice.core.design_system.theme.SemiBold14
import com.easyhz.noffice.core.design_system.theme.White

@Stable
@Composable
internal fun PlaceBottomSheetTopBar(
    modifier: Modifier = Modifier,
    placeUrl: String,
    onClickBack: () -> Unit,
) {
    Box(
        modifier = modifier
            .padding(top = 8.dp)
            .screenHorizonPadding()
            .fillMaxWidth()
            .background(White)
            .height(44.dp)
            .borderBottom(Grey50, 1.dp)
    ) {

        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .sizeIn(minHeight = 32.dp, minWidth = 32.dp)
                .noRippleClickable { onClickBack() },
            contentAlignment = Alignment.CenterStart
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.ic_chevron_left),
                contentDescription = "left",
                tint = Grey400
            )
        }


        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .widthIn(max = 300.dp)
        ) {
            Text(
                text = placeUrl,
                style = SemiBold14,
                color = Grey700,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

