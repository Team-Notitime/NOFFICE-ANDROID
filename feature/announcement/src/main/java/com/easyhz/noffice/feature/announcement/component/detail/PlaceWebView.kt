package com.easyhz.noffice.feature.announcement.component.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.easyhz.noffice.core.design_system.component.webView.NofficeWebView

@Composable
internal fun PlaceWebView(
    modifier: Modifier = Modifier,
    url: String,
    onLoading: (Boolean) -> Unit
) {
    val state = rememberScrollState()
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .verticalScroll(state)
    ) {
        NofficeWebView(
            url = url,
            onLoading = onLoading
        )
    }

}