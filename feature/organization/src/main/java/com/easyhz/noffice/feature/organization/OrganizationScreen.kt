package com.easyhz.noffice.feature.organization

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
fun OrganizationScreen() {
    NofficeScaffold(
        topBar = {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(text = "organization")
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(it)) {
            Text(text = "organization", modifier = Modifier.align(Alignment.Center))
        }
    }
}