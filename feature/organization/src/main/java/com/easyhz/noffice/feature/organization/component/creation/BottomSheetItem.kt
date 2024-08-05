package com.easyhz.noffice.feature.organization.component.creation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.theme.Grey100
import com.easyhz.noffice.core.design_system.theme.SubTitle1
import com.easyhz.noffice.feature.organization.util.creation.BottomSheetItem

@Composable
internal fun BottomSheetItems(
    modifier: Modifier = Modifier,
    bottomSheetItem: Array<BottomSheetItem>,
    onClick: (BottomSheetItem) -> Unit
) {
    Column(modifier = modifier.padding(vertical = 8.dp, horizontal = 8.dp)) {
        bottomSheetItem.forEachIndexed { index, item ->
            BottomSheetItem(
                bottomSheetItem = item,
                onClick = { onClick(item) }
            )
            if (index != bottomSheetItem.lastIndex) {
                Divider(modifier = Modifier.padding(horizontal = 8.dp).fillMaxWidth().width(1.dp).background(Grey100))
            }
        }
    }
}

@Composable
private fun BottomSheetItem(
    modifier: Modifier = Modifier,
    bottomSheetItem: BottomSheetItem,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .heightIn(min = 44.dp)
            .clickable { onClick() },
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.padding(horizontal = 8.dp),
            painter = painterResource(id = bottomSheetItem.iconId),
            contentDescription = bottomSheetItem.iconId.toString(),
            tint = bottomSheetItem.color
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = bottomSheetItem.stringId),
            style = SubTitle1,
            color = bottomSheetItem.color
        )
    }
}