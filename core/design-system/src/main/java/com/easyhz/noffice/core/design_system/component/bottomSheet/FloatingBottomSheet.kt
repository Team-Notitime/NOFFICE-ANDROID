package com.easyhz.noffice.core.design_system.component.bottomSheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.theme.DimColor
import com.easyhz.noffice.core.design_system.theme.White
import com.easyhz.noffice.core.design_system.util.bottomSheet.animatePadding


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FloatingBottomSheet(
    modifier: Modifier = Modifier,
    backgroundColor: Color = White,
    onDismissRequest: () -> Unit,
    sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
    dragHandle: @Composable (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    BottomSheet(
        modifier = modifier.navigationBarsPadding(),
        sheetState = sheetState,
        dragHandle = dragHandle,
        onDismissRequest = onDismissRequest
    ) {
        Box(
            modifier = Modifier
                .navigationBarsPadding()
                .padding(bottom = 32.dp)
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(12.dp))
                .fillMaxWidth()
                .background(backgroundColor)
        ) {
            content()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FloatingExpandedBottomSheet(
    modifier: Modifier = Modifier,
    backgroundColor: Color = White,
    isExpanded: Boolean,
    height: Dp = 0.dp,
    minHeight: Dp = 300.dp,
    onDismissRequest: () -> Unit,
    sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true,),
    dragHandle: @Composable (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    var scrimColor by remember { mutableStateOf(DimColor) }
    val horizontalPadding by animatePadding(targetValue = if (isExpanded) 0.dp else 16.dp)
    val bottomPadding by animatePadding(targetValue = if (isExpanded) 0.dp else 16.dp)
    val round by animatePadding(targetValue = if (isExpanded) 0.dp else 24.dp)
    val contentHeight by animatePadding(targetValue = if (isExpanded) screenHeight else height)

    LaunchedEffect(isExpanded) {
        scrimColor = if (isExpanded) White else DimColor
    }

    BottomSheet(
        modifier = modifier,
        sheetState = sheetState,
        dragHandle = dragHandle,
        scrimColor = scrimColor,
        onDismissRequest = onDismissRequest
    ) {
        Box(
            modifier = Modifier
                .padding(bottom = bottomPadding)
                .padding(horizontal = horizontalPadding)
                .clip(RoundedCornerShape(round))
                .fillMaxWidth()
                .heightIn(min = minHeight)
                .height(contentHeight)
                .background(backgroundColor)
        ) {
            content()
        }
    }
}