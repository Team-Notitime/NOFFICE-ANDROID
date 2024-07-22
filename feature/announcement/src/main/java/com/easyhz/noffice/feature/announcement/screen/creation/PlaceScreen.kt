package com.easyhz.noffice.feature.announcement.screen.creation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeBasicScaffold

@Composable
fun PlaceScreen(
    modifier: Modifier = Modifier,
    viewModel: CreationViewModel = hiltViewModel(),
    navigateToUp: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    NofficeBasicScaffold {
        Box(modifier = modifier.fillMaxSize()) {
            Text(text = "content ${uiState.healthCheck}", modifier = Modifier.align(Alignment.Center).clickable { navigateToUp() })
        }
    }

}