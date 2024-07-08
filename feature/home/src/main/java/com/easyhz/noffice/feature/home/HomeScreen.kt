package com.easyhz.noffice.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeScaffold

@Composable
fun HomeScreen() {
    NofficeScaffold(
        topBar = {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(text = "Home")
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(it)) {
            Text(text = "home", modifier = Modifier.align(Alignment.Center))
        }
    }
}