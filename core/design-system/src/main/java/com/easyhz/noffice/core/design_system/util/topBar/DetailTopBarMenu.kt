package com.easyhz.noffice.core.design_system.util.topBar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable

@Immutable
data class DetailTopBarMenu(
    val content: @Composable (() -> Unit),
    val onClick: () -> Unit
)