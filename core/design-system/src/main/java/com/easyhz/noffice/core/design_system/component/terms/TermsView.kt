package com.easyhz.noffice.core.design_system.component.terms

import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun TermsView(
    modifier: Modifier = Modifier,
    fileName: String
) {
    val context = LocalContext.current
    AndroidView(
        modifier = modifier,
        factory = { _ ->
            WebView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                settings.apply {
                    cacheMode = WebSettings.LOAD_DEFAULT
                    textZoom = 100
                }
                webChromeClient = WebChromeClient()
            }
        },
        update = {
            it.loadUrl("file:///android_asset/${fileName}")
        }
    )
}


