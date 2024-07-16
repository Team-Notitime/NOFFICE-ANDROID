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
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    title: String?,
    placeholder: String,
    isFilled: Boolean,
    maxCount: Int? = null,
    singleLine: Boolean,
    minLines: Int = 1,
    icon: TextFieldIcon,
    onClickIcon: (() -> Unit) = {},
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
            color = Grey800
        ),
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
                textCount = value.text.length,
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
        value = TextFieldValue("내용이 잇음"),
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
        value = TextFieldValue(""),
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
        value = TextFieldValue(""),
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
        value = TextFieldValue(""),
        onValueChange = { },
        title = null,
        placeholder = "내용dmf dlqfur 입력",
        maxCount = 10,
        isFilled = false,
        singleLine = true,
        icon = TextFieldIcon.CLEAR
    )
}