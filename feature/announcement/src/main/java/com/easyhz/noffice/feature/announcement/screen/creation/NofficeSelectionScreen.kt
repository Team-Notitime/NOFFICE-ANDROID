package com.easyhz.noffice.feature.announcement.screen.creation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeBasicScaffold

@Composable
fun NofficeSelectionScreen(
    modifier: Modifier = Modifier,
    navigateToAnnouncementCreationContent: () -> Unit
) {
    NofficeBasicScaffold {
        Column(modifier = modifier.fillMaxSize()
            , horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "selection ", modifier = Modifier)
            Button(onClick = { navigateToAnnouncementCreationContent() }) {
                Text(text = "navigate")
            }
        }
    }
}