package com.easyhz.noffice.core.design_system.component.scaffold

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun NofficeScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = { },
    bottomBar: @Composable () -> Unit = { },
    snackBarHost: @Composable () -> Unit = { },
    floatingActionButton: @Composable () -> Unit = { },
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor: Color = Color.Transparent,
    contentColor: Color = Color.Black, // FIXME
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = topBar,
        bottomBar = bottomBar,
        snackbarHost = snackBarHost,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        contentColor = contentColor,
        containerColor =  containerColor,
        contentWindowInsets = contentWindowInsets,
    ) {
        content(it)
    }
}