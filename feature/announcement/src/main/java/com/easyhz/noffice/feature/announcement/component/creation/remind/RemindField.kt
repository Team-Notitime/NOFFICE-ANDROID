package com.easyhz.noffice.feature.announcement.component.creation.remind

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.component.chip.NofficeChip
import com.easyhz.noffice.core.design_system.theme.Blue100
import com.easyhz.noffice.core.design_system.theme.Blue500
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.theme.Grey50
import com.easyhz.noffice.core.design_system.theme.Grey700
import com.easyhz.noffice.core.design_system.theme.SemiBold16
import com.easyhz.noffice.core.design_system.util.chip.ChipState
import com.easyhz.noffice.core.design_system.util.chip.ChipStyle
import com.easyhz.noffice.core.design_system.util.chip.ChipStyles
import com.easyhz.noffice.feature.announcement.contract.creation.remind.secondsToString

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun RemindField(
    modifier: Modifier = Modifier,
    selectList: List<String>,
    onClick: (String) -> Unit
) {
    FlowRow(
        modifier = modifier
            .animateContentSize()
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Grey50)
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        maxItemsInEachRow = 4
    ) {
        when (selectList.size) {
            0 -> {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = com.easyhz.noffice.core.design_system.R.string.announcement_creation_option_remind_field_empty),
                    style = SemiBold16,
                    color = Grey400,
                    textAlign = TextAlign.Center
                )
            }
            else -> {
                selectList.forEach {
                    AnimatedVisibility(visible = true) {
                    key(it) {
                            NofficeChip(
                                chipModifier = Modifier,
                                text = secondsToString(it),
                                selectState = ChipState.Picked,
                                chipStyles = ChipStyles(
                                    unSelected = ChipStyle(),
                                    selected = ChipStyle(),
                                    picked = ChipStyle(
                                        containerColor = Blue100,
                                        contentColor = Blue500,
                                        iconColor = null
                                    )
                                )
                            ) { text, _ -> onClick(text) }
                        }
                    }
                }
                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = stringResource(id = com.easyhz.noffice.core.design_system.R.string.announcement_creation_option_remind_field_suffix),
                    style = SemiBold16,
                    color = Grey700,
                    textAlign = TextAlign.Justify,
                )
            }
        }
    }
}