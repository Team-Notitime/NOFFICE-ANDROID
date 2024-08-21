package com.easyhz.noffice.feature.announcement.screen.creation.task

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.noffice.core.common.util.collectInSideEffectWithLifecycle
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.bottomSheet.FloatingBottomSheet
import com.easyhz.noffice.core.design_system.component.button.TaskItem
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeBasicScaffold
import com.easyhz.noffice.core.design_system.component.topBar.DetailTopBar
import com.easyhz.noffice.core.design_system.extension.noRippleClickable
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.theme.Red
import com.easyhz.noffice.core.design_system.theme.SubTitle1
import com.easyhz.noffice.core.design_system.util.topBar.DetailTopBarMenu
import com.easyhz.noffice.feature.announcement.component.creation.CreationTitle
import com.easyhz.noffice.feature.announcement.component.creation.task.TaskButton
import com.easyhz.noffice.feature.announcement.component.creation.task.TaskInput
import com.easyhz.noffice.feature.announcement.contract.creation.CreationIntent
import com.easyhz.noffice.feature.announcement.contract.creation.task.TaskIntent
import com.easyhz.noffice.feature.announcement.contract.creation.task.TaskSideEffect
import com.easyhz.noffice.feature.announcement.screen.creation.CreationViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(
    modifier: Modifier = Modifier,
    viewModel: TaskViewModel = hiltViewModel(),
    creationViewModel: CreationViewModel = hiltViewModel(),
    taskList: List<String>? = emptyList(),
    navigateToUp: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }
    val scrollState = rememberLazyListState()
    LaunchedEffect(key1 = Unit) {
        viewModel.postIntent(TaskIntent.InitScreen(taskList))
    }

    NofficeBasicScaffold(
        modifier = Modifier.noRippleClickable { viewModel.postIntent(TaskIntent.ClearFocus) },
        bottomBar = {
            AnimatedContent(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .screenHorizonPadding(),
                targetState = uiState.isVisibleTextField,
                label = "input"
            ) { isVisible ->
                when (isVisible) {
                    true -> {
                        TaskInput(
                            modifier = Modifier.focusRequester(focusRequester),
                            value = uiState.taskText,
                            onValueChange = { viewModel.postIntent(TaskIntent.ChangeTaskTextValue(it)) },
                        ) {
                            viewModel.postIntent(TaskIntent.SaveTask)
                        }
                    }

                    false -> {
                        TaskButton(
                            modifier = Modifier.padding(vertical = 8.dp),
                            onClickTaskButton = { viewModel.postIntent(TaskIntent.ClickTaskButton) },
                            onClickSaveButton = { viewModel.postIntent(TaskIntent.ClickSaveButton) }
                        )
                    }
                }
            }
        },
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
                    onClick = { viewModel.postIntent(TaskIntent.ClickBackButton) }
                ),
                title = stringResource(id = R.string.announcement_creation_title),
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            CreationTitle(
                modifier = Modifier.screenHorizonPadding(),
                title = stringResource(id = R.string.announcement_creation_option_task_title)
            )
            LazyColumn(
                state = scrollState,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                itemsIndexed(
                    items = uiState.taskList,
                    key = { _, task -> task.id }
                ) { index, task ->
                    TaskItem(
                        text = task.text,
                        isComplete = false,
                        onLongClick = {
                            viewModel.postIntent(TaskIntent.OpenDeleteBottomSheet(index))
                        }
                    ) { }
                }
            }
        }
        if (uiState.openBottomSheet != -1) {
            FloatingBottomSheet(
                modifier = Modifier.padding(bottom = 32.dp),
                onDismissRequest = { viewModel.postIntent(TaskIntent.CloseDeleteBottomSheet) }
            ) {
                Row(
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 16.dp)
                        .heightIn(min = 44.dp)
                        .clickable { viewModel.postIntent(TaskIntent.DeleteTask(uiState.openBottomSheet))},
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        painter = painterResource(id = R.drawable.ic_trash),
                        contentDescription = "delete",
                        tint = Red
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.delete),
                        style = SubTitle1,
                        color = Red
                    )
                }
            }
        }
    }

    viewModel.sideEffect.collectInSideEffectWithLifecycle { sideEffect ->
        when (sideEffect) {
            is TaskSideEffect.NavigateToUp -> { navigateToUp() }
            is TaskSideEffect.NavigateToNext -> {
                creationViewModel.postIntent(CreationIntent.SaveOptionData(sideEffect.data))
                navigateToUp()
            }
            is TaskSideEffect.ClearFocus -> {
                focusManager.clearFocus()
            }

            is TaskSideEffect.RequestFocus -> {
                delay(500)
                focusRequester.requestFocus()
            }

            is TaskSideEffect.ScrollToBottom -> {
                scrollState.animateScrollToItem(sideEffect.index)
            }
        }
    }

}