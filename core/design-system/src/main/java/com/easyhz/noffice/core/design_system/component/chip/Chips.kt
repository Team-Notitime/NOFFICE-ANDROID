package com.easyhz.noffice.core.design_system.component.chip

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.extension.noRippleClickable
import com.easyhz.noffice.core.design_system.theme.Green500
import com.easyhz.noffice.core.design_system.theme.Green800
import com.easyhz.noffice.core.design_system.theme.Grey100
import com.easyhz.noffice.core.design_system.theme.Grey300
import com.easyhz.noffice.core.design_system.theme.Grey500
import com.easyhz.noffice.core.design_system.util.chip.ChipItem
import com.easyhz.noffice.core.design_system.util.chip.ChipState
import com.easyhz.noffice.core.design_system.util.chip.ChipStyle
import com.easyhz.noffice.core.design_system.util.chip.ChipStyles
import com.easyhz.noffice.core.design_system.util.chip.handleChipClick
import com.easyhz.noffice.core.design_system.util.chip.styleFor

/**
 * FIXME 디자이너 피드백 후 수정
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ChipGroup(
    chipItems: List<ChipItem>,
    modifier: Modifier = Modifier,
    spacing: Dp = 6.dp,
    content: @Composable (Int, String) -> Unit
) {
    var selectIndex by rememberSaveable { mutableIntStateOf(-1) }

    FlowRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        maxItemsInEachRow = 5,
        horizontalArrangement = Arrangement.spacedBy(spacing),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        chipItems.forEachIndexed { index, item ->
            NofficeChip(
                chipModifier = Modifier,
                text = item.label,
                selectState = item.selectState.value,
                chipStyles = ChipStyles(
                    unSelected = ChipStyle(
                        containerColor = Grey100,
                        contentColor = Grey500,
                        iconColor = null
                    ),
                    selected = ChipStyle(
                        containerColor = Green500.copy(alpha = 0.16f),
                        contentColor = Green800,
                        iconColor = Green800.copy(alpha = 0.3f)
                    ),
                    picked = ChipStyle()
                )
            ) { label, state ->
                chipItems.handleChipClick(index) { newIndex ->
                    selectIndex = newIndex
                }
            }
        }
    }

    if (selectIndex != -1) {
        content(selectIndex, chipItems[selectIndex].label)
    } else {
        Text(text = "")
    }
}
@Composable
fun NofficeChip(
    modifier: Modifier = Modifier,
    chipModifier: Modifier,
    text: String,
    selectState: ChipState,
    chipStyles: ChipStyles,
    onChipClicked: (String, ChipState) -> Unit,
) {
    val chipStyle = remember(selectState) { chipStyles.styleFor(selectState) }

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .noRippleClickable { onChipClicked(text, selectState) }
            .background(chipStyle.containerColor)
            .animateContentSize()
            .padding(vertical = 4.dp, horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            color = chipStyle.contentColor,
            style = chipStyle.textStyle,
            modifier = chipModifier
        )
        chipStyle.iconColor?.let {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(id = R.drawable.ic_check),
                contentDescription = "check",
                tint = it
            )
        }
    }
}

/** ====================
 *      Preview
 * ======================
 */

@Preview(group = "badge-on", name = "none")
@Composable
private fun OnNonePrev() {
    NofficeChip(
        chipModifier = Modifier,
        text = "장소",
        selectState = ChipState.Picked,
        chipStyles = ChipStyles(
            unSelected = ChipStyle(),
            selected = ChipStyle(),
            picked = ChipStyle(
                iconColor = null
            )
        )
    ) { _, _ -> }
}

@Preview(group = "badge-on", name = "icon")
@Composable
private fun OnIconPrev() {
    NofficeChip(
        chipModifier = Modifier,
        text = "장소",
        selectState = ChipState.Picked,
        chipStyles = ChipStyles(
            unSelected = ChipStyle(),
            selected = ChipStyle(),
            picked = ChipStyle()
        )
    ) { _, _ -> }
}

@Preview(group = "badge-weak", name = "none")
@Composable
private fun WeakNonePrev() {
    NofficeChip(
        chipModifier = Modifier,
        text = "장소",
        selectState = ChipState.Selected,
        chipStyles = ChipStyles(
            unSelected = ChipStyle(),
            selected = ChipStyle(
                containerColor = Green500.copy(alpha = 0.16f),
                contentColor = Green800,
                iconColor = null
            ),
            picked = ChipStyle()
        )
    ) { _, _ -> }
}

@Preview(group = "badge-weak", name = "icon")
@Composable
private fun WeakIconPrev() {
    NofficeChip(
        chipModifier = Modifier,
        text = "장소",
        selectState = ChipState.Selected,
        chipStyles = ChipStyles(
            unSelected = ChipStyle(),
            selected = ChipStyle(
                containerColor = Green500.copy(alpha = 0.16f),
                contentColor = Green800,
                iconColor = Green800.copy(alpha = 0.3f)
            ),
            picked = ChipStyle()
        )
    ) { _, _ -> }
}

@Preview(group = "badge-off", name = "none")
@Composable
private fun OffNonePrev() {
    NofficeChip(
        chipModifier = Modifier,
        text = "장소",
        selectState = ChipState.Unselected,
        chipStyles = ChipStyles(
            unSelected = ChipStyle(
                containerColor = Grey100,
                contentColor = Grey500,
                iconColor = null
            ),
            selected = ChipStyle(),
            picked = ChipStyle()
        )
    ) { _, _ -> }
}

@Preview(group = "badge-off", name = "icon")
@Composable
private fun OffIconPrev() {
    NofficeChip(
        chipModifier = Modifier,
        text = "장소",
        selectState = ChipState.Unselected,
        chipStyles = ChipStyles(
            unSelected = ChipStyle(
                containerColor = Grey100,
                contentColor = Grey500,
                iconColor = Grey300
            ),
            selected = ChipStyle(),
            picked = ChipStyle()
        )
    ) { _, _ -> }
}