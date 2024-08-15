package com.easyhz.noffice.feature.announcement.component.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.button.TaskItem
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Blue500
import com.easyhz.noffice.core.design_system.theme.Grey800
import com.easyhz.noffice.core.design_system.theme.SemiBold18
import com.easyhz.noffice.core.design_system.theme.White
import com.easyhz.noffice.core.model.task.Task


internal fun LazyListScope.taskListField(
    taskList: List<Task>,
    onClickTask: (Int) -> Unit,
) {
    if (taskList.isEmpty()) return
    item {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                .background(White)
                .padding(vertical = 16.dp)
                .screenHorizonPadding(),
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
    }
    itemsIndexed(taskList, key = { _, task -> task.content }) { index, task ->
        TaskItem(
            modifier = Modifier
                .background(White),
            text = task.content, isComplete = task.isDone
        ) {
            onClickTask(index)
        }
    }
    item {
        Spacer(
            modifier = Modifier
                .height(16.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp))
                .background(White)
        )
    }
}