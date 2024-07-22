package com.easyhz.noffice.feature.announcement.screen.creation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.noffice.core.common.util.collectInSideEffectWithLifecycle
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeBasicScaffold
import com.easyhz.noffice.core.design_system.component.topBar.DetailTopBar
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.util.topBar.DetailTopBarMenu
import com.easyhz.noffice.feature.announcement.contract.creation.CreationIntent
import com.easyhz.noffice.feature.announcement.contract.creation.CreationSideEffect

@Composable
fun ContentScreen(
    modifier: Modifier = Modifier,
    viewModel: CreationViewModel = hiltViewModel(),
    navigateToUp: () -> Unit,
    navigateToPlace: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    NofficeBasicScaffold(
        topBar = {
            DetailTopBar(
                leadingItem = DetailTopBarMenu(
                    content = {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.ic_chevron_left),
                            contentDescription = "left",
                            tint = Grey400
                        )
                    },
                    onClick = { viewModel.postIntent(CreationIntent.ClickBackButton) }
                ),
                title = stringResource(id = R.string.announcement_creation_title),
            )
        }
    ) {
        Box(modifier = modifier.fillMaxSize()) {
            Text(text = "content ${uiState.healthCheck}", modifier = Modifier.align(Alignment.Center).clickable {  })
        }
    }
    viewModel.sideEffect.collectInSideEffectWithLifecycle {sideEffect ->
        when(sideEffect) {
            is CreationSideEffect.NavigateToUp -> { navigateToUp() }
            is CreationSideEffect.NavigateToNext -> { navigateToPlace() }
        }
    }
}