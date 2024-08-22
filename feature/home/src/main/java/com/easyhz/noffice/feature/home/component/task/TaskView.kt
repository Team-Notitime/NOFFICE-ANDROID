package com.easyhz.noffice.feature.home.component.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.easyhz.noffice.core.design_system.component.button.TaskItem
import com.easyhz.noffice.core.design_system.component.exception.ExceptionView
import com.easyhz.noffice.core.design_system.theme.White
import com.easyhz.noffice.core.design_system.util.exception.ExceptionType
import com.easyhz.noffice.feature.home.component.common.OrganizationTaskHeader
import com.easyhz.noffice.feature.home.component.viewmodel.TaskViewModel

// TODO 체크하면 밑으로 내려가는 로직 추가 필요
@Composable
internal fun TaskView(
    modifier: Modifier = Modifier,
    viewModel: TaskViewModel = hiltViewModel(),
    onClickOrganizationHeader: (organizationId: Int, organizationTitle: String) -> Unit,
) {
    val taskList = viewModel.taskListState.collectAsLazyPagingItems()
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(top = 28.dp, bottom = 48.dp),
        verticalArrangement = Arrangement.spacedBy(28.dp)
    ) {
        items(taskList.itemCount) { index ->
            taskList[index]?.let { item ->
                Column(
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

                        }
                    }
                    if(item.tasks.isEmpty()) {
                        ExceptionView(
                            modifier = Modifier.height(300.dp).fillMaxWidth(),
                            type = ExceptionType.NO_TASK
                        )
                    }
                }
            }
        }
    }
    if (taskList.itemCount == 0) {
        ExceptionView(
            modifier = Modifier.fillMaxSize(),
            type = ExceptionType.NO_ORGANIZATION
        )
    }
}