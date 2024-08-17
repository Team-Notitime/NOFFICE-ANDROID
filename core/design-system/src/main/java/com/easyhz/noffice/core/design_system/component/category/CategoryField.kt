package com.easyhz.noffice.core.design_system.component.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.component.button.CheckButton
import com.easyhz.noffice.core.design_system.component.button.CheckButtonDefaults
import com.easyhz.noffice.core.design_system.theme.Green100
import com.easyhz.noffice.core.design_system.theme.Green700
import com.easyhz.noffice.core.design_system.theme.Grey50
import com.easyhz.noffice.core.design_system.theme.Grey600
import com.easyhz.noffice.core.model.organization.category.Category

@Composable
fun CategoryField(
    modifier: Modifier = Modifier,
    categoryList: List<Category>,
    onClick: (Int) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier.padding(top = 8.dp),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        itemsIndexed(categoryList, key = { index, _ -> index /* FIXME */ }) { index, item ->
            CheckButton(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = item.title,
                isComplete = item.isSelected,
                color = CheckButtonDefaults(
                    completeContainerColor = Green100,
                    completeContentColor = Green700,
                    completeIconColor = null,
                    incompleteContainerColor = Grey50,
                    incompleteContentColor = Grey600,
                    incompleteIconColor = null
                )
            ) {
                onClick(index)
            }
        }
    }
}