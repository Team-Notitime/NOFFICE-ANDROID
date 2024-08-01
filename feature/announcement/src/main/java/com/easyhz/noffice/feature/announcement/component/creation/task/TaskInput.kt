package com.easyhz.noffice.feature.announcement.component.creation.task

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.button.IconMediumButton
import com.easyhz.noffice.core.design_system.component.button.MediumButton
import com.easyhz.noffice.core.design_system.component.textField.SubTextField
import com.easyhz.noffice.core.design_system.extension.noRippleClickable
import com.easyhz.noffice.core.design_system.theme.Green100
import com.easyhz.noffice.core.design_system.theme.Green600
import com.easyhz.noffice.core.design_system.theme.Grey300

@Composable
internal fun TaskButton(
    modifier: Modifier = Modifier,
    onClickTaskButton: () -> Unit,
    onClickSaveButton: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        IconMediumButton(
            text = stringResource(id = R.string.announcement_creation_option_task_button),
            iconId = R.drawable.ic_plus,
            contentColor = Green600,
            containerColor = Green100,
            onClick = onClickTaskButton
        )
        MediumButton(
            modifier = Modifier
                .fillMaxWidth(),
            text = stringResource(id = R.string.announcement_creation_option_button),
            enabled = true,
            onClick = onClickSaveButton
        )
    }
}

@Composable
internal fun TaskInput(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    onSave: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        SubTextField(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 4.dp),
            value = value,
            onValueChange = onValueChange,
            placeholder = stringResource(id = R.string.announcement_creation_option_task_placeholder),
            isFilled = false,
            singleLine = false,
            icon = null,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                onSave()
            })
        )
        Box(modifier = Modifier
            .widthIn(min = 32.dp)
            .heightIn(min = 56.dp)
            .noRippleClickable { onSave() },
            contentAlignment = Alignment.CenterEnd
        ) {
            Icon(
                modifier = Modifier.size(32.dp).align(Alignment.Center),
                painter = painterResource(id = R.drawable.ic_send),
                contentDescription = "send",
                tint = Grey300
            )
        }
    }
}