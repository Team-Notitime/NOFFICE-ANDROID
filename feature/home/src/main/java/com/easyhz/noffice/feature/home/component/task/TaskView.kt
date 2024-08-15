package com.easyhz.noffice.feature.home.component.task

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.component.button.CheckButton
import com.easyhz.noffice.feature.home.component.common.OrganizationHeader

// TODO 체크하면 밑으로 내려가는 로직 추가 필요
@Composable
internal fun TaskView(
    modifier: Modifier = Modifier,
) {
    val check = remember { mutableStateOf(false) }
    val check2 = remember { mutableStateOf(false) }
    Column(modifier = modifier) {
//        ExceptionView(
//            modifier = Modifier.fillMaxSize(),
//            type = ExceptionType.NO_ORGANIZATION
//        )
        OrganizationHeader( // FIXME 고칠 필요가 있음
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .padding(vertical = 8.dp),
            organizationName = "CMC 15th"
        ) { }

//        ExceptionView(
//            modifier = Modifier.fillMaxSize(),
//            type = ExceptionType.NO_TASK
//        )
        Column( // FIXME 고칠 필요가 있음
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CheckButton(
                modifier = Modifier.fillMaxWidth(),
                text = "할 일",
                isComplete = check.value,
            ) {
                check.value = !check.value
            }
            CheckButton(
                modifier = Modifier.fillMaxWidth(),
                text = "할 일2",
                isComplete = check2.value,
            ) {
                check2.value = !check2.value
            }
        }
    }
}