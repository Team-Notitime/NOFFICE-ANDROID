package com.easyhz.noffice.feature.my_page.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Green600
import com.easyhz.noffice.core.design_system.theme.Grey200
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.theme.Grey50
import com.easyhz.noffice.core.design_system.theme.Grey500
import com.easyhz.noffice.core.design_system.theme.SubBody12
import com.easyhz.noffice.core.design_system.theme.SubBody14
import com.easyhz.noffice.core.design_system.theme.White
import com.easyhz.noffice.feature.my_page.util.GetVersionName
import com.easyhz.noffice.feature.my_page.util.MenuType
import com.easyhz.noffice.feature.my_page.util.MyPageMenu
import com.easyhz.noffice.feature.my_page.util.MyPageSection

@Composable
internal fun SectionItem(
    modifier: Modifier = Modifier,
    section: MyPageSection,
    isChecked: Boolean,
    onClick: (MyPageMenu) -> Unit,
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(White)
            .padding(vertical = 12.dp)
            .screenHorizonPadding(),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            text = stringResource(id = section.titleId),
            style = SubBody12,
            color = Grey500,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        section.items.forEach {
            MenuItem(
                modifier = Modifier.fillMaxWidth(),
                menu = it,
                isChecked = isChecked
            ) {
                onClick(it)
            }
        }
    }
}

@Composable
private fun MenuItem(
    modifier: Modifier = Modifier,
    menu: MyPageMenu,
    isChecked: Boolean,
    onClick: () -> Unit
) {
    val versionName = GetVersionName()
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(menu.type.isClickable()) {
                onClick()
            }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(id = menu.titleId),
            style = SubBody14,
            color = menu.textColor
        )

        when (menu.type) {
            MenuType.MORE -> {
                Icon(
                    modifier = Modifier.size(18.dp),
                    painter = painterResource(id = R.drawable.ic_chevron_right_sub),
                    contentDescription = "more",
                    tint = Grey500
                )
            }

            MenuType.TOGGLE -> {
                Box(
                    modifier = Modifier.width(32.dp).height(20.dp)
                ) {
                    Switch(
                        modifier = Modifier.scale(0.6f).align(Alignment.CenterEnd),
                        checked = isChecked,
                        onCheckedChange = null,
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = White,
                            checkedTrackColor = Green600,
                            uncheckedThumbColor = White,
                            uncheckedTrackColor = Grey200,
                            uncheckedBorderColor = Color.Transparent
                        )
                    )
                }
            }

            MenuType.VERSION_TEXT -> {
                Text(text = versionName, style = SubBody14, color = Grey400)
            }
        }
    }
}