package com.easyhz.noffice.feature.my_page.screen.detail.noticeDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeBasicScaffold
import com.easyhz.noffice.core.design_system.component.topBar.DetailTopBar
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Body15
import com.easyhz.noffice.core.design_system.theme.Grey100
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.theme.Grey50
import com.easyhz.noffice.core.design_system.theme.SemiBold18
import com.easyhz.noffice.core.design_system.theme.SubBody14
import com.easyhz.noffice.core.design_system.util.topBar.DetailTopBarMenu
import com.easyhz.noffice.core.model.notice.Notice

@Composable
fun NoticeDetailScreen(
    modifier: Modifier = Modifier,
    item: Notice,
    navigateToUp: () -> Unit
) {
    val scrollState = rememberScrollState()
    NofficeBasicScaffold(
        containerColor = Grey50,
        statusBarColor = Grey50,
        navigationBarColor = Grey50,
        topBar = {
            DetailTopBar(
                modifier = Modifier.background(Grey50),
                leadingItem = DetailTopBarMenu(
                    content = {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.ic_chevron_left),
                            contentDescription = "left",
                            tint = Grey400
                        )
                    },
                    onClick = { navigateToUp() }
                ),
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .screenHorizonPadding()
                .padding(horizontal = 8.dp)
                .padding(top = 12.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(text = item.title, style = SemiBold18, lineHeight = (18 * 1.3).sp)
                Text(text = item.date, style = SubBody14, color = Grey400)
            }
            HorizontalDivider(
                thickness = 1.dp,
                color = Grey100
            )
            Text(
                modifier = Modifier.padding(bottom = 12.dp),
                text = item.content,
                style = Body15,
                lineHeight = (15 * 1.4).sp
            )
        }
    }
}