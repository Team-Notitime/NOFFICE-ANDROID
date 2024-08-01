package com.easyhz.noffice.feature.announcement.component.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.component.button.TaskItem
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.skeleton.SkeletonProvider
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Blue500
import com.easyhz.noffice.core.design_system.theme.Grey800
import com.easyhz.noffice.core.design_system.theme.SemiBold18
import com.easyhz.noffice.core.design_system.theme.White
import com.easyhz.noffice.core.model.task.Task

@Composable
internal fun TaskListField(
    modifier: Modifier = Modifier,
    taskList: List<Task>,
    isLoading: Boolean,
    onClickTask: (Int) -> Unit,
) {
    SkeletonProvider(isLoading = isLoading && taskList.isEmpty(), skeletonContent = { }) {
        Column(
            modifier = modifier
                .clip(RoundedCornerShape(8.dp))
                .background(White)
                .padding(vertical = 16.dp)
        ) {
            Row(
                modifier = Modifier.padding(bottom = 12.dp).screenHorizonPadding(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.announcement_detail_task_list_header),
                    style = SemiBold18,
                    color = Grey800
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_check),
                    contentDescription = "check",
                    tint = Blue500
                )
            }
            taskList.forEachIndexed { index, task ->
                TaskItem(text = task.content, isComplete = task.isDone) {
                    onClickTask(index)
                }
            }
        }
    }
}