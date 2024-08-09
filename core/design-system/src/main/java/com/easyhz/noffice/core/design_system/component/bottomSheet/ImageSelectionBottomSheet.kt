package com.easyhz.noffice.core.design_system.component.bottomSheet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.theme.Grey100
import com.easyhz.noffice.core.design_system.theme.SubTitle1
import com.easyhz.noffice.core.design_system.util.bottomSheet.ImageSelectionBottomSheetItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageSelectionBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
    isEmptyProfile: Boolean,
    onDismissRequest: () -> Unit,
    onClick: (ImageSelectionBottomSheetItem) -> Unit
) {
    FloatingBottomSheet(
        modifier = modifier.padding(bottom = 32.dp),
        sheetState = sheetState,
        roundedCornerShape = RoundedCornerShape(24.dp),
        onDismissRequest = onDismissRequest
    ) {
        ImageSelectionBottomSheetItems(
            bottomSheetItem = enumValues<ImageSelectionBottomSheetItem>().copyOfRange(
                0,
                if (isEmptyProfile) 2 else 3
            ),
            onClick = onClick
        )
    }
}

@Composable
internal fun ImageSelectionBottomSheetItems(
    modifier: Modifier = Modifier,
    bottomSheetItem: Array<ImageSelectionBottomSheetItem>,
    onClick: (ImageSelectionBottomSheetItem) -> Unit
) {
    Column(modifier = modifier.padding(vertical = 8.dp, horizontal = 8.dp)) {
        bottomSheetItem.forEachIndexed { index, item ->
            BottomSheetItem(
                bottomSheetItem = item,
                onClick = { onClick(item) }
            )
            if (index != bottomSheetItem.lastIndex) {
                HorizontalDivider(modifier = Modifier.padding(horizontal = 8.dp).fillMaxWidth().width(1.dp).background(
                    Grey100
                ))
            }
        }
    }
}

@Composable
private fun BottomSheetItem(
    modifier: Modifier = Modifier,
    bottomSheetItem: ImageSelectionBottomSheetItem,
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