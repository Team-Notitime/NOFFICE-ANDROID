package com.easyhz.noffice.core.design_system.component.textField

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.extension.borderBottom
import com.easyhz.noffice.core.design_system.theme.Grey100
import com.easyhz.noffice.core.design_system.theme.Grey300
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.theme.Grey800
import com.easyhz.noffice.core.design_system.theme.SubBody14
import com.easyhz.noffice.core.design_system.theme.semiBold
import com.easyhz.noffice.core.design_system.util.textField.TextFieldState
import com.easyhz.noffice.core.design_system.util.textField.getTextFieldState

@Composable
fun UnderBarTextField(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    placeholder: String,
    isFilled: Boolean,
    maxCount: Int? = null,
    singleLine: Boolean,
    minLines: Int = 1,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    val state = getTextFieldState(text = value.text, isFilled = isFilled)

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        textStyle = semiBold(20).copy(
            color = Grey800
        ),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        minLines = minLines,
        visualTransformation = visualTransformation,
        decorationBox = { innerTextField ->
            UnderBarTextFieldContainer(
                modifier = Modifier
                    .height(54.dp),
                state = state,
                placeholder = placeholder,
                textCount = value.text.length,
                maxCount = maxCount,
                innerTextField = innerTextField
            )
        }
    )
}

@Composable
private fun UnderBarTextFieldContainer(
    modifier: Modifier = Modifier,
    placeholder: String,
    maxCount: Int?,
    textCount: Int,
    state: TextFieldState,
    innerTextField: @Composable () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .borderBottom(width = 1.dp , color = Grey100),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.weight(1f)) {
            innerTextField()
            if (state == TextFieldState.Default) {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 2.dp),
                    text = placeholder,
                    style = semiBold(20),
                    color = Grey300
                )
            }
        }

        maxCount?.let {
            Text(
                text = "$textCount/$it",
                style = SubBody14,
                color = Grey400
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun MainTextFieldPrev() {
    UnderBarTextField(
        value = TextFieldValue("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ"),
        onValueChange = { },
        placeholder = "내용으 입력",
        isFilled = false,
        singleLine = true,
        maxCount = 10
    )
}