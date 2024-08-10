package com.easyhz.noffice.core.design_system.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.easyhz.noffice.core.design_system.component.button.MediumButton
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Grey100
import com.easyhz.noffice.core.design_system.theme.Grey600
import com.easyhz.noffice.core.design_system.theme.SemiBold16
import com.easyhz.noffice.core.design_system.theme.White
import com.easyhz.noffice.core.design_system.util.dialog.InputDialogButton

@Composable
fun MainDialog(
    modifier: Modifier = Modifier,
    negativeButton: InputDialogButton,
    positiveButton: InputDialogButton,
    content: @Composable () -> Unit
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
                .clip(RoundedCornerShape(24.dp))
                .background(color = White)
                .padding(horizontal = 20.dp, vertical = 16.dp)
                .padding(top = 16.dp, bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            content()
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                MediumButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = negativeButton.text,
                    enabled = true,
                    contentColor = Grey600,
                    containerColor = Grey100,
                    textStyle = SemiBold16,
                    onClick = negativeButton.onClick
                )
                MediumButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = positiveButton.text,
                    enabled = true,
                    textStyle = SemiBold16,
                    onClick = positiveButton.onClick
                )
            }
        }
    }
}