package com.easyhz.noffice.core.design_system.component.textField

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.easyhz.noffice.core.design_system.extension.noRippleClickable
import com.easyhz.noffice.core.design_system.theme.Blue100
import com.easyhz.noffice.core.design_system.theme.Blue600
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.theme.SemiBold14
import com.easyhz.noffice.core.design_system.theme.SubBody14
import com.easyhz.noffice.core.design_system.util.textField.TextFieldIcon
import com.easyhz.noffice.core.design_system.util.textField.TextFieldState
import com.easyhz.noffice.core.design_system.util.textField.getTextFieldState

@Composable
fun SubTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isFilled: Boolean,
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
        modifier = modifier.clip(RoundedCornerShape(8.dp)).background(Blue100),
        textStyle = SemiBold14.copy(
            color = Blue600,
            lineHeight = (16 * 1.5).sp
        ),
        readOnly = readOnly,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        minLines = minLines,
        visualTransformation = visualTransformation,
        decorationBox = { innerTextField ->
            SubTextFieldContainer(
                modifier = Modifier
                    .heightIn(min = 48.dp),
                state = state,
                placeholder = placeholder,
                icon = icon,
                onClickIcon = {
                    onClickIcon()
                },
                innerTextField = innerTextField,
            )
        }
    )
}

@Composable
private fun SubTextFieldContainer(
    modifier: Modifier = Modifier,
    placeholder: String,
    state: TextFieldState,
    icon: TextFieldIcon?,
    onClickIcon: (() -> Unit),
    innerTextField: @Composable () -> Unit
) {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextFieldContainerContent(
                state = state,
                placeholder = placeholder,
                icon = icon,
                onClickIcon = onClickIcon,
                innerTextField = innerTextField,
            )
        }
    }
}

@Composable
private fun TextFieldContainerContent(
    modifier: Modifier = Modifier,
    state: TextFieldState,
    placeholder: String,
    icon: TextFieldIcon?,
    onClickIcon: (() -> Unit),
    innerTextField: @Composable () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.padding(vertical = 10.dp).weight(1f)) {
            innerTextField()
            if (state == TextFieldState.Default) {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 2.dp),
                    text = placeholder,
                    style = SubBody14,
                    color = Grey400,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        if (state != TextFieldState.Default && icon != null) {
            Box(
                modifier = Modifier
                    .sizeIn(minHeight = 32.dp, minWidth = 32.dp)
                    .noRippleClickable { onClickIcon() }) {
                Image(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    painter = painterResource(id = icon.resId),
                    contentDescription = icon.name
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainTextFieldPlaceholderPrev() {
    SubTextField(
        value = "lkk",
        onValueChange = { },
        placeholder = "내용dmf dlqfur 입력",
        isFilled = false,
        singleLine = true,
        icon = null
    )
}