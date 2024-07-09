package com.easyhz.noffice.feature.home.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeScaffold
import com.easyhz.noffice.feature.home.component.HomeTopBar

@Composable
fun HomeScreen() {
    NofficeScaffold(
        topBar = {
            HomeTopBar {
                println("click")
            }
        }
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            Text(text = "home", modifier = Modifier.align(Alignment.Center))
        }
    }
}