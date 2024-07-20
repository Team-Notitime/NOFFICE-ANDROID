package com.easyhz.noffice.core.design_system.component.textField

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.theme.Grey800
import com.easyhz.noffice.core.design_system.theme.SubBody16
import com.easyhz.noffice.core.design_system.util.textField.TextFieldIcon
import com.easyhz.noffice.core.design_system.util.textField.getTextFieldState

@Composable
fun MainTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    title: String?,
    placeholder: String,
    isFilled: Boolean,
    maxCount: Int? = null,
    readOnly: Boolean = false,
    singleLine: Boolean,
    minLines: Int = 1,
    icon: TextFieldIcon? = TextFieldIcon.CLEAR,
    onClickIcon: (() -> Unit) = {},
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    val state = getTextFieldState(text = value, isFilled = isFilled)

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        textStyle = SubBody16.copy(
            color = Grey800
        ),
        readOnly = readOnly,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        minLines = minLines,
        visualTransformation = visualTransformation,
        decorationBox = { innerTextField ->
            TextFieldContainer(
                modifier = Modifier
                    .height(48.dp),
                title = title,
                state = state,
                placeholder = placeholder,
                icon = icon,
                onClickIcon = {
                    onClickIcon()
                },
                textCount = value.length,
                maxCount = maxCount,
                innerTextField = innerTextField
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun MainTextFieldPrev() {
    MainTextField(
        value = "내용이 잇음",
        onValueChange = { },
        title = null,
        placeholder = "내용으 입력",
        isFilled = false,
        singleLine = true,
        icon = TextFieldIcon.CLEAR
    )
}

@Preview(showBackground = true)
@Composable
private fun MainTextFieldPlaceholderPrev() {
    MainTextField(
        value = "",
        onValueChange = { },
        title = null,
        placeholder = "내용dmf dlqfur 입력",
        isFilled = false,
        singleLine = true,
        icon = TextFieldIcon.CLEAR
    )
}

@Preview(showBackground = true)
@Composable
private fun MainTextFieldTitlePrev() {
    MainTextField(
        value = "",
        onValueChange = { },
        title = "내용",
        placeholder = "내용dmf dlqfur 입력",
        isFilled = false,
        singleLine = true,
        icon = TextFieldIcon.CLEAR
    )
}

@Preview(showBackground = true)
@Composable
private fun MainTextFieldMaxCountPrev() {
    MainTextField(
        value = "",
        onValueChange = { },
        title = null,
        placeholder = "내용dmf dlqfur 입력",
        maxCount = 10,
        isFilled = false,
        singleLine = true,
        icon = TextFieldIcon.CLEAR
    )
}