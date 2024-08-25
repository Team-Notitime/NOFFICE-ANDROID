package com.easyhz.noffice.feature.home.component.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.easyhz.noffice.core.common.util.collectInSideEffectWithLifecycle
import com.easyhz.noffice.core.design_system.component.button.TaskItem
import com.easyhz.noffice.core.design_system.component.exception.ExceptionView
import com.easyhz.noffice.core.design_system.theme.Green500
import com.easyhz.noffice.core.design_system.theme.White
import com.easyhz.noffice.core.design_system.util.exception.ExceptionType
import com.easyhz.noffice.feature.home.component.common.OrganizationTaskHeader
import com.easyhz.noffice.feature.home.component.viewmodel.TaskViewModel
import com.easyhz.noffice.feature.home.contract.task.TaskIntent
import com.easyhz.noffice.feature.home.contract.task.TaskSideEffect

// TODO 체크하면 밑으로 내려가는 로직 추가 필요
@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun TaskView(
    modifier: Modifier = Modifier,
    viewModel: TaskViewModel = hiltViewModel(),
    onClickOrganizationHeader: (organizationId: Int, organizationTitle: String) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val taskList = viewModel.taskListState.collectAsLazyPagingItems()
    val isRefreshing = remember(taskList.loadState.refresh) {
        taskList.loadState.refresh == LoadState.Loading
    }

    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing && uiState.isRefreshing,
        onRefresh = { viewModel.postIntent(TaskIntent.Refresh) }
    )

    Box(modifier = modifier.pullRefresh(pullRefreshState)) {
        LazyColumn(
            contentPadding = PaddingValues(bottom = 28.dp),
        ) {
            items(taskList.itemCount) { index ->
                taskList[index]?.let { item ->
                    if(item.tasks.isEmpty()) return@items
                    Column(
                        modifier = Modifier.padding(vertical = 14.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OrganizationTaskHeader(
                            modifier = Modifier
                                .fillMaxWidth(),
                            organizationName = item.organizationName
                        ) { onClickOrganizationHeader(item.organizationId, item.organizationName) }
                        item.tasks.forEach { task ->
                            TaskItem(
                                modifier = Modifier
                                    .background(White),
                                text = task.content, isComplete = task.isDone
                            ) {
                                viewModel.postIntent(TaskIntent.UpdateTaskStatus(task))
                            }
                        }
    //                    if(item.tasks.isEmpty()) {
    //                        ExceptionView(
    //                            modifier = Modifier.height(300.dp).fillMaxWidth(),
    //                            type = ExceptionType.NO_TASK
    //                        )
    //                    }
                    }
                }
            }
        }
        if (!uiState.isRefreshing) {
            PullRefreshIndicator(
                refreshing = isRefreshing,
                contentColor = Green500,
                state = pullRefreshState,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 20.dp)
            )
        }
    }
    if (taskList.itemCount == 0) {
        ExceptionView(
            modifier = Modifier.fillMaxSize(),
            type = ExceptionType.NO_ORGANIZATION
        )
    }

    viewModel.sideEffect.collectInSideEffectWithLifecycle { sideEffect ->
        when(sideEffect) {
            is TaskSideEffect.Refresh -> {
                taskList.refresh()
            }
        }
    }
}