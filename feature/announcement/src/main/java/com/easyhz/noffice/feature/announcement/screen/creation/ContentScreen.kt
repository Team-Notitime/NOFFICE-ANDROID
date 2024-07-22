package com.easyhz.noffice.feature.announcement.screen.creation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeBasicScaffold

@Composable
fun ContentScreen(
    modifier: Modifier = Modifier
) {
    NofficeBasicScaffold {
        Box(modifier = modifier.fillMaxSize()) {
            Text(text = "selection ", modifier = Modifier.align(Alignment.Center))
        }
    }
}