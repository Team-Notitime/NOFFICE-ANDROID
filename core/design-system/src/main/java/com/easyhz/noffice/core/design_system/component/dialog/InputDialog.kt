package com.easyhz.noffice.core.design_system.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.easyhz.noffice.core.design_system.component.button.MediumButton
import com.easyhz.noffice.core.design_system.component.textField.UnderBarTextField
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Grey100
import com.easyhz.noffice.core.design_system.theme.Grey600
import com.easyhz.noffice.core.design_system.theme.Grey800
import com.easyhz.noffice.core.design_system.theme.InputDialogTitle
import com.easyhz.noffice.core.design_system.theme.White
import com.easyhz.noffice.core.design_system.util.dialog.InputDialogButton

@Composable
fun InputDialog(
    modifier: Modifier = Modifier,
    title: String,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    placeholder: String,
    maxCount: Int,
    negativeButton: InputDialogButton,
    positiveButton: InputDialogButton
) {
    Dialog(
        onDismissRequest = { },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false
        ),
    ) {
        Column(
            modifier = modifier
                .screenHorizonPadding()
                .clip(RoundedCornerShape(20.dp))
                .background(color = White)
                .padding(horizontal = 20.dp)
                .padding(top = 16.dp, bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .padding(start = 4.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = title,
                    style = InputDialogTitle
                )
            }
            UnderBarTextField(
                value = value,
                onValueChange = onValueChange,
                placeholder = placeholder,
                isFilled = false,
                singleLine = true,
                maxCount = maxCount
            )
            Spacer(modifier = Modifier.height(19.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                MediumButton(
                    text = negativeButton.text,
                    enabled = true,
                    contentColor = Grey600,
                    containerColor = Grey100,
                    onClick = negativeButton.onClick
                )
                MediumButton(
                    text = positiveButton.text,
                    enabled = true,
                    contentColor = Grey800,
                    onClick = positiveButton.onClick
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
//    device = "spec:shape=Normal,width=240,height=640, unit=dp, dpi= 480"
)
@Composable
fun DialogPreview() {
    val visibleDialog by remember { mutableStateOf(true) }
    if (visibleDialog) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            InputDialog(
                title = "그룹 가입이 수락 됐는데\n실명으로 할 건지 아닌지 얼른 고르셈 지금 고르지 않으면..",
                value = TextFieldValue(),
                onValueChange = { },
                placeholder = "실명을 입력하세요",
                maxCount = 10,
                negativeButton = InputDialogButton(
                    "실명으로 할래"
                ) { },
                positiveButton = InputDialogButton(
                    "확인"
                ) { }
            )
        }
    }
}