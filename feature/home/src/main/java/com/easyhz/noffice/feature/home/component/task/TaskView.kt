package com.easyhz.noffice.feature.home.component.task

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.component.exception.ExceptionView
import com.easyhz.noffice.core.design_system.util.exception.ExceptionType
import com.easyhz.noffice.feature.home.component.common.OrganizationHeader

@Composable
internal fun TaskView(
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
//        ExceptionView(
//            modifier = Modifier.fillMaxSize(),
//            type = ExceptionType.NO_ORGANIZATION
//        )
        OrganizationHeader(
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .padding(horizontal = 30.dp),
            organizationName = "CMC 15th"
        ) { }

        ExceptionView(
            modifier = Modifier.fillMaxSize(),
            type = ExceptionType.NO_TASK
        )
    }

}