package com.easyhz.noffice.core.design_system.component.textField

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.easyhz.noffice.core.design_system.theme.Grey800
import com.easyhz.noffice.core.design_system.theme.SubBody16
import com.easyhz.noffice.core.design_system.util.textField.TextFieldIcon
import com.easyhz.noffice.core.design_system.util.textField.TextFieldType
import com.easyhz.noffice.core.design_system.util.textField.getTextFieldState

@Composable
fun TextField(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    title: String?,
    placeholder: String,
    isFilled: Boolean,
    maxCount: Int? = null,
    readOnly: Boolean = false,
    singleLine: Boolean,
    textFieldType: TextFieldType = TextFieldType.MULTIPLE,
    minLines: Int = 1,
    icon: TextFieldIcon? = TextFieldIcon.CLEAR,
    onClickIcon: (() -> Unit) = {},
    onTextLayout: (TextLayoutResult) -> Unit = {},
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    val state = getTextFieldState(text = value.text, isFilled = isFilled)

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        textStyle = SubBody16.copy(
            color = Grey800,
            lineHeight = (16 * 1.5).sp
        ),
        readOnly = readOnly,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        minLines = minLines,
        visualTransformation = visualTransformation,
        onTextLayout = onTextLayout,
        decorationBox = { innerTextField ->
            TextFieldContainer(
                modifier = Modifier
                    .heightIn(min = 48.dp),
                title = title,
                state = state,
                placeholder = placeholder,
                icon = icon,
                onClickIcon = {
                    onClickIcon()
                },
                textCount = value.text.length,
                maxCount = maxCount,
                innerTextField = innerTextField,
                textFieldType = textFieldType
            )
        }
    )
}