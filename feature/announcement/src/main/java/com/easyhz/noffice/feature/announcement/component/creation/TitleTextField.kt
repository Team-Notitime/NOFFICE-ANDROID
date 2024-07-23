package com.easyhz.noffice.feature.announcement.component.creation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.component.textField.MainTextField
import com.easyhz.noffice.core.design_system.component.textField.TextField
import com.easyhz.noffice.core.design_system.theme.SemiBold16
import com.easyhz.noffice.core.design_system.util.textField.TextFieldType

@Composable
internal fun TitleTextField(
    modifier: Modifier = Modifier,
    textFieldType: TextFieldType,
    title: String,
    placeholder: String,
    singleLine: Boolean,
    value: String,
    onChangeValue: (String) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = title, style = SemiBold16
        )
        MainTextField(
            value = value,
            onValueChange = onChangeValue,
            title = null,
            textFieldType = textFieldType,
            placeholder = placeholder,
            isFilled = false,
            singleLine = singleLine,
            icon = null
        )
    }
}

@Composable
internal fun ContentTextField(
    modifier: Modifier = Modifier,
    textFieldModifier: Modifier = Modifier,
    textFieldType: TextFieldType,
    title: String,
    placeholder: String,
    value: TextFieldValue,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    onChangeValue: (TextFieldValue) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = title, style = SemiBold16
        )
        TextField(
            modifier = textFieldModifier,
            onTextLayout = onTextLayout,
            value = value,
            onValueChange = onChangeValue,
            title = null,
            textFieldType = textFieldType,
            placeholder = placeholder,
            isFilled = false,
            singleLine = false,
            icon = null,
        )
    }
}
