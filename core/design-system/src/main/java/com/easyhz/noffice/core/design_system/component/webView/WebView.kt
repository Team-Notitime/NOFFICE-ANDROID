package com.easyhz.noffice.core.design_system.component.webView

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.easyhz.noffice.core.design_system.util.webView.nofficeWebViewClient

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun NofficeWebView(
    modifier: Modifier = Modifier,
    webView: WebView,
    url: String,
    onGoBack: (Boolean) -> Unit,
    onLoading: (Boolean) -> Unit
) {
    AndroidView(
        modifier = modifier.padding(bottom = 48.dp),
        factory = { view ->
            webView.apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                settings.apply {
                    javaScriptEnabled = true
                    allowFileAccess = true
                    allowContentAccess = true
                    domStorageEnabled = true
                    mediaPlaybackRequiresUserGesture = false
                    cacheMode = WebSettings.LOAD_DEFAULT
                    textZoom = 100
                }
                webChromeClient = WebChromeClient()
                webViewClient = nofficeWebViewClient(
                    canGoBack = { onGoBack(it) },
                    onLoading = onLoading
                )
            }
        },
        update = {
            it.loadUrl(url)
        }
    )
}