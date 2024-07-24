package com.easyhz.noffice.core.design_system.component.textField

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.easyhz.noffice.core.design_system.extension.noRippleClickable
import com.easyhz.noffice.core.design_system.theme.Grey100
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.theme.SemiBold14
import com.easyhz.noffice.core.design_system.theme.SubBody12
import com.easyhz.noffice.core.design_system.theme.SubBody14
import com.easyhz.noffice.core.design_system.util.textField.TextFieldIcon
import com.easyhz.noffice.core.design_system.util.textField.TextFieldState
import com.easyhz.noffice.core.design_system.util.textField.TextFieldType

@Composable
internal fun TextFieldContainer(
    modifier: Modifier = Modifier,
    textFieldType: TextFieldType,
    title: String?,
    placeholder: String,
    maxCount: Int?,
    textCount: Int,
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
            verticalAlignment = textFieldType.verticalAlignment
        ) {
            title?.let {
                TextFieldContainerTitle(
                    title = title,
                )
            }
            TextFieldContainerContent(
                modifier = modifier.heightIn(min = textFieldType.minHeight).border(
                    width = 1.dp,
                    color = Grey100,
                    shape = RoundedCornerShape(8.dp)
                ),
                textFieldType = textFieldType,
                state = state,
                placeholder = placeholder,
                icon = icon,
                onClickIcon = onClickIcon,
                innerTextField = innerTextField,
            )
        }
        maxCount?.let {
            TextFieldCaption(
                modifier = Modifier,
                textCount = textCount,
                maxCount = it
            )
        }
    }
}

@Composable
private fun TextFieldContainerTitle(
    title: String,
) {
    Text(
        text = title,
        style = SemiBold14,
        color = Grey400,
        lineHeight = (14 * 1.2).sp
    )
}

@Composable
private fun TextFieldContainerContent(
    modifier: Modifier = Modifier,
    textFieldType: TextFieldType,
    state: TextFieldState,
    placeholder: String,
    icon: TextFieldIcon?,
    onClickIcon: (() -> Unit),
    innerTextField: @Composable () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = textFieldType.verticalAlignment
    ) {
        Box(modifier = Modifier.padding(vertical = textFieldType.verticalPadding).weight(1f)) {
            innerTextField()
            if (state == TextFieldState.Default) {
                Text(
                    modifier = Modifier.align(Alignment.CenterStart).padding(start = 2.dp),
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
                    modifier = Modifier.padding(top = textFieldType.verticalPadding).align(Alignment.CenterEnd),
                    painter = painterResource(id = icon.resId),
                    contentDescription = icon.name
                )
            }
        }
    }
}

@Composable
private fun TextFieldCaption(
    modifier: Modifier,
    textCount: Int,
    maxCount: Int
) {
    Box(modifier = modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.align(Alignment.CenterEnd),
            text = "$textCount/$maxCount",
            style = SubBody12,
            color = Grey400
        )
    }
}