package com.easyhz.noffice.core.design_system.component.terms

import android.webkit.WebView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun TermsView(
    modifier: Modifier = Modifier,
    fileName: String
) {
    AndroidView(
        modifier = modifier,
        factory = { ctx ->
            WebView(ctx).apply {
                loadUrl("file:///android_asset/${fileName}")
            }
        }
    )
}


