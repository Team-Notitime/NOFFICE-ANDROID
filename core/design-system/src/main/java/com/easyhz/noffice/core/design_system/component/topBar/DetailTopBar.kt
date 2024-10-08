package com.easyhz.noffice.core.design_system.component.topBar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.extension.noRippleClickable
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Green700
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.theme.Grey700
import com.easyhz.noffice.core.design_system.theme.SemiBold16
import com.easyhz.noffice.core.design_system.theme.semiBold
import com.easyhz.noffice.core.design_system.util.topBar.DetailTopBarMenu

@Composable
fun DetailTopBar(
    modifier: Modifier = Modifier,
    leadingItem: DetailTopBarMenu? = null,
    title: String? = null,
    trailingItem: DetailTopBarMenu? = null,
) {
    Box(
        modifier = modifier
            .padding(top = 8.dp)
            .screenHorizonPadding()
            .fillMaxWidth()
            .height(44.dp),
    ) {
        leadingItem?.let { item ->
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .sizeIn(minHeight = 32.dp, minWidth = 32.dp)
                    .noRippleClickable { item.onClick() },
                contentAlignment = Alignment.CenterStart
            ) {
                item.content()
            }
        }
        title?.let {
            Box(
                modifier = Modifier
                    .sizeIn(minHeight = 32.dp, minWidth = 32.dp)
                    .align(Alignment.Center),
                contentAlignment = Alignment.Center
            ) {
                Text(text = it, style = SemiBold16, color = Grey700)
            }
        }
        trailingItem?.let { item ->
            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .sizeIn(minHeight = 32.dp, minWidth = 32.dp)
                    .noRippleClickable { item.onClick() },
                contentAlignment = Alignment.CenterEnd
            ) {
                item.content()
            }
        }
    }
}

@Preview(showBackground = true, name = "top-detail")
@Composable
private fun DetailTopBarTopDetailPrev() {
    DetailTopBar(
        leadingItem = DetailTopBarMenu(
            content = {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.ic_chevron_left),
                    contentDescription = "left",
                    tint = Grey400
                )
            },
            onClick = { }
        ),
        title = "안녕",
        trailingItem = DetailTopBarMenu(
            content = {
                Text(text = "완료", style = semiBold(18), color = Green700)
            },
            onClick = { }
        )
    )
}

@Preview(showBackground = true, name = "top-right")
@Composable
private fun DetailTopBarTopRightPrev() {
    DetailTopBar(
        leadingItem = DetailTopBarMenu(
            content = {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.ic_chevron_left),
                    contentDescription = "left",
                    tint = Grey400
                )
            },
            onClick = { }
        ),
        trailingItem = DetailTopBarMenu(
            content = {
                Text(text = "완료", style = semiBold(18), color = Green700)
            },
            onClick = { }
        )
    )
}