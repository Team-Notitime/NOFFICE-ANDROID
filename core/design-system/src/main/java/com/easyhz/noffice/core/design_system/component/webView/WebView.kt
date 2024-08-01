package com.easyhz.noffice.core.design_system.component.webView

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.easyhz.noffice.core.design_system.util.webView.nofficeWebViewClient

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun NofficeWebView(
    modifier: Modifier = Modifier,
    url: String,
    onLoading: (Boolean) -> Unit
) {
    AndroidView(
        modifier = modifier,
        factory = { WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            settings.apply {
                javaScriptEnabled = true
                allowFileAccess = true
                allowContentAccess = true
            }
            webViewClient = nofficeWebViewClient(onLoading)
        } },
        update = { it.loadUrl(url)}
    )
}