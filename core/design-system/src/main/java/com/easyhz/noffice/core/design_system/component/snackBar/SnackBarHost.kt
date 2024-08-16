package com.easyhz.noffice.core.design_system.component.snackBar

import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NofficeSnackBarHost(
    modifier: Modifier = Modifier,
    hostState: SnackbarHostState,
) {
    SnackbarHost(
        modifier = modifier,
        hostState = hostState
    ) {
        NofficeSnackBar(snackBarData = it)
    }
}