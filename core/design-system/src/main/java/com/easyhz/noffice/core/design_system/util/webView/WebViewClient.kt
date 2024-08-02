package com.easyhz.noffice.core.design_system.util.webView

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.webkit.URLUtil
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import java.net.URISyntaxException

fun nofficeWebViewClient(
    canGoBack: (Boolean) -> Unit,
    onLoading: (Boolean) -> Unit
) = object : WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        return view?.context?.handleUrl(request?.url.toString()) ?: false
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        canGoBack(view?.canGoBack() ?: false)
        onLoading(true)
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        onLoading(false)
    }
}
private fun Context.handleUrl(url: String): Boolean {
    if (!URLUtil.isNetworkUrl(url) && !URLUtil.isJavaScriptUrl(url)) {
        val uri = try {
            Uri.parse(url)
        } catch (e: Exception) {
            return false
        }
        return when (uri.scheme) {
            "intent" -> {
                startSchemeIntent(url)
            }
            else -> {
                return try {
                    startActivity(Intent(Intent.ACTION_VIEW, uri))
                    true
                } catch (e: Exception) {
                    false
                }
            }
        }
    } else {
        return false
    }
}

private fun Context.startSchemeIntent(url: String): Boolean {
    val schemeIntent: Intent = try {
        Intent.parseUri(url, Intent.URI_INTENT_SCHEME)
    } catch (e: URISyntaxException) {
        return false
    }
    try {
        startActivity(schemeIntent)
        return true
    } catch (e: ActivityNotFoundException) {
        val packageName = schemeIntent.getPackage()

        if (!packageName.isNullOrBlank()) {
            // TODO: 논의 사항
//            startActivity(
//                Intent(
//                    Intent.ACTION_VIEW,
//                    Uri.parse("market://details?id=$packageName")
//                )
//            )
            return true
        }
    }
    return false
}