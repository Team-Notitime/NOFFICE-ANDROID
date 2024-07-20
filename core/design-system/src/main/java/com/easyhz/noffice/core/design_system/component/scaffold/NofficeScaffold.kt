package com.easyhz.noffice.core.design_system.component.scaffold

import android.app.Activity
import android.view.View
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import com.easyhz.noffice.core.design_system.theme.Grey50
import com.easyhz.noffice.core.design_system.theme.White

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
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            updateWindowColors(
                view = view,
                statusBarColor = null,
                navigationBarColor = Grey50
            )
        }
    }
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

@Composable
fun NofficeBasicScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = { },
    bottomBar: @Composable () -> Unit = { },
    snackBarHost: @Composable () -> Unit = { },
    floatingActionButton: @Composable () -> Unit = { },
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor: Color = Color.Transparent,
    contentColor: Color = Color.Black, // FIXME
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    statusBarColor: Color? = null,
    navigationBarColor: Color? = White,
    content: @Composable (PaddingValues) -> Unit
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            updateWindowColors(
                view = view,
                statusBarColor = statusBarColor,
                navigationBarColor = navigationBarColor
            )
        }
    }
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

private fun updateWindowColors(
    view: View,
    statusBarColor: Color? = null,
    navigationBarColor: Color? = null
) {
    val window = (view.context as Activity).window
    statusBarColor?.let {
        window.statusBarColor = it.toArgb()
    }

    navigationBarColor?.let {
        window.navigationBarColor = it.toArgb()
    }
}