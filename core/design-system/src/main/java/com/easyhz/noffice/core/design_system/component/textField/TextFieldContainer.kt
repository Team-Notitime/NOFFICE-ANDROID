package com.easyhz.noffice.core.design_system.component.textField

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.extension.noRippleClickable
import com.easyhz.noffice.core.design_system.theme.Grey100
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.theme.SemiBold14
import com.easyhz.noffice.core.design_system.theme.SubBody12
import com.easyhz.noffice.core.design_system.theme.SubBody14
import com.easyhz.noffice.core.design_system.util.textField.TextFieldIcon
import com.easyhz.noffice.core.design_system.util.textField.TextFieldState

@Composable
internal fun TextFieldContainer(
    modifier: Modifier = Modifier,
    title: String?,
    placeholder: String,
    maxCount: Int?,
    textCount: Int,
    state: TextFieldState,
    icon: TextFieldIcon,
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
            title?.let {
                TextFieldContainerTitle(
                    title = title,
                )
            }
            TextFieldContainerContent(
                modifier = modifier.border(
                    width = 1.dp,
                    color = Grey100,
                    shape = RoundedCornerShape(8.dp)
                ),
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
        color = Grey400
    )
}

@Composable
private fun TextFieldContainerContent(
    modifier: Modifier = Modifier,
    state: TextFieldState,
    placeholder: String,
    icon: TextFieldIcon,
    onClickIcon: (() -> Unit),
    innerTextField: @Composable () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.weight(1f)) {
            innerTextField()
            if (state == TextFieldState.Default) {
                Text(
                    modifier = Modifier.align(Alignment.CenterStart).padding(start = 2.dp),
                    text = placeholder,
                    style = SubBody14,
                    color = Grey400
                )
            }
        }
        if (state != TextFieldState.Default) {
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