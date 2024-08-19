package com.easyhz.noffice.feature.announcement.screen.creation.remind

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.noffice.core.common.util.collectInSideEffectWithLifecycle
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.button.MediumButton
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeBasicScaffold
import com.easyhz.noffice.core.design_system.component.topBar.DetailTopBar
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.util.topBar.DetailTopBarMenu
import com.easyhz.noffice.feature.announcement.component.creation.CreationTitle
import com.easyhz.noffice.feature.announcement.component.creation.remind.CustomRemindButton
import com.easyhz.noffice.feature.announcement.component.creation.remind.RemindField
import com.easyhz.noffice.feature.announcement.component.creation.remind.RemindItem
import com.easyhz.noffice.feature.announcement.contract.creation.CreationIntent
import com.easyhz.noffice.feature.announcement.contract.creation.remind.RemindIntent
import com.easyhz.noffice.feature.announcement.contract.creation.remind.RemindSideEffect
import com.easyhz.noffice.feature.announcement.screen.creation.CreationViewModel

@Composable
fun RemindScreen(
    modifier: Modifier = Modifier,
    viewModel: RemindViewModel = hiltViewModel(),
    creationViewModel: CreationViewModel = hiltViewModel(),
    selectRemind: List<String>? = null,
    navigateToCustomRemind: () -> Unit,
    navigateToUp: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = Unit) {
        viewModel.postIntent(RemindIntent.InitScreen(selectRemind))
    }

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
                    onClick = { viewModel.postIntent(RemindIntent.ClickBackButton) }
                ),
                title = stringResource(id = R.string.announcement_creation_title),
            )
        },
        bottomBar = {
            MediumButton(
                modifier = Modifier
                    .screenHorizonPadding()
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                text = stringResource(id = R.string.announcement_creation_option_button),
                enabled = true
            ) {
                viewModel.postIntent(RemindIntent.ClickSaveButton)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .screenHorizonPadding()
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            CreationTitle(
                title = stringResource(id = R.string.announcement_creation_option_remind_title)
            )
            RemindField(
                modifier = Modifier.padding(vertical = 12.dp),
                selectList = uiState.remindMap.filter { it.value }.map { it.key }.toList()
            ) {
                viewModel.postIntent(RemindIntent.ClickRemindItem(it))
            }
            LazyColumn(
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(uiState.remindMap.toList(), key = { it.first }) {(text, isSelected)->
                    RemindItem(
                        text = text,
                        isSelected = isSelected
                    ) {
                        viewModel.postIntent(RemindIntent.ClickRemindItem(text))
                    }
                }
                item {
                    CustomRemindButton(
                        modifier = Modifier.padding(bottom = 16.dp)
                    ) {
                        viewModel.postIntent(RemindIntent.ClickCustomRemindButton)
                    }
                }
            }
        }
    }

    viewModel.sideEffect.collectInSideEffectWithLifecycle {sideEffect ->
        when(sideEffect) {
            is RemindSideEffect.NavigateToUp -> { navigateToUp() }
            is RemindSideEffect.NavigateToNext -> {
                creationViewModel.postIntent(CreationIntent.SaveOptionData(sideEffect.data))
                navigateToUp()
            }
            is RemindSideEffect.NavigateToCustomRemind -> {
                navigateToCustomRemind()
            }
        }
    }
}