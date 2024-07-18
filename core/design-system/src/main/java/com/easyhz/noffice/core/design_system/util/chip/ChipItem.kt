package com.easyhz.noffice.core.design_system.util.chip

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.easyhz.noffice.core.design_system.theme.Green300
import com.easyhz.noffice.core.design_system.theme.Green500
import com.easyhz.noffice.core.design_system.theme.Grey800
import com.easyhz.noffice.core.design_system.theme.SubHeading15

data class ChipItem(
    var label: String,
    val selectState: MutableState<ChipState>
)

enum class ChipState {
    Unselected, // off
    Selected,   // weak
    Picked, // on
}

data class ChipStyle(
    val containerColor: Color = Green500,
    val contentColor: Color = Grey800,
    val iconColor: Color? = Green300,
    val textStyle: TextStyle = SubHeading15
)

data class ChipStyles(
    val unSelected: ChipStyle,
    val selected: ChipStyle,
    val picked: ChipStyle
)


fun List<String>.toChipState(): List<ChipItem> =
    this.map { ChipItem(label = it, selectState = mutableStateOf(ChipState.Unselected)) }

fun ChipStyles.styleFor(selectState: ChipState): ChipStyle {
    return when (selectState) {
        ChipState.Unselected -> unSelected
        ChipState.Selected -> selected
        ChipState.Picked -> picked
    }
}

fun List<ChipItem>.handleChipClick(
    index: Int,
    onSelectIndexChanged: (Int) -> Unit
) {
    val currentChipState = this[index].selectState.value

    this.forEachIndexed { idx, item ->
        item.selectState.value = when {
            idx == index -> if (currentChipState == ChipState.Picked) ChipState.Unselected else ChipState.Picked    // picked 였다면 unselected, 그기 아니면 picked
            item.selectState.value == ChipState.Picked -> ChipState.Selected    // picked가 있으면 selected 로 변경
            else -> item.selectState.value
        }
    }
    val newIndex = if (this[index].selectState.value == ChipState.Picked) {
        index
    } else {
        val selectedIndex = this.indexOfFirst { it.selectState.value == ChipState.Selected && this.indexOf(it) > index }
        val finalIndex = if (selectedIndex != -1) {
            selectedIndex
        } else {
            this.indexOfFirst { it.selectState.value == ChipState.Selected }
        }

        if (finalIndex != -1) {
            this[finalIndex].selectState.value = ChipState.Picked
        }
        finalIndex
    }

    onSelectIndexChanged(newIndex)
}