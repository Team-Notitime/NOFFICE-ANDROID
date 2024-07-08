package com.easyhz.noffice.core.design_system.component.bottomBar

import androidx.annotation.DrawableRes
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeScaffold
import com.easyhz.noffice.core.design_system.extension.noRippleClickable
import com.easyhz.noffice.core.design_system.theme.Green500
import com.easyhz.noffice.core.design_system.theme.Grey300
import com.easyhz.noffice.core.design_system.util.BottomMenu


@Composable
fun HomeBottomBar(
    modifier: Modifier = Modifier,
    current: BottomMenu,
    onClick: (BottomMenu) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(49.dp)
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomMenu.entries.forEach { item ->
            if (item != BottomMenu.ADD) {
                BottomBarItem(
                    modifier = Modifier.weight(1f),
                    iconId = item.iconId,
                    label = item.label,
                    selected = item == current,
                    onClick = { onClick(item) },
                )
            } else {
                Box(
                    modifier = Modifier.width(72.dp).noRippleClickable { onClick(BottomMenu.ADD) },
                )
            }
        }
    }
}


@Composable
internal fun BottomBarItem(
    modifier: Modifier = Modifier,
    @DrawableRes iconId: Int,
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .selectable(
                selected = selected,
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = iconId),
            tint = if (selected) Green500 else Grey300,
            contentDescription = label,
        )
    }
}

@Preview(backgroundColor = 0xFFFFFFFF)
@Composable
private fun BottomBarPrev() {
    NofficeScaffold(
        bottomBar = {
            HomeBottomBar(current = BottomMenu.HOME, onClick = { })
        }
    ) {

    }
}