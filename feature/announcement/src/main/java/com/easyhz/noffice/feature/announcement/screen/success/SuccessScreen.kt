package com.easyhz.noffice.feature.announcement.screen.success

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.button.MediumButton
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeBasicScaffold
import com.easyhz.noffice.core.design_system.component.topBar.DetailTopBar
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Grey100
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.theme.Grey600
import com.easyhz.noffice.core.design_system.theme.SubTitle1
import com.easyhz.noffice.core.design_system.theme.Title4
import com.easyhz.noffice.core.design_system.theme.White
import com.easyhz.noffice.core.design_system.util.topBar.DetailTopBarMenu

@Composable
fun SuccessScreen(
    modifier: Modifier = Modifier,
    id: Int,
    title: String,
    navigateToHome: () -> Unit,
    navigateToAnnouncementDetail: (Int, String) -> Unit,
) {
    NofficeBasicScaffold(
        statusBarColor = White,
        navigationBarColor = White,
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
                    onClick = { navigateToHome() }
                ),
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .screenHorizonPadding(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                MediumButton(
                    modifier = Modifier.weight(1f),
                    text = stringResource(id = R.string.announcement_creation_success_to_home),
                    contentColor = Grey600,
                    containerColor = Grey100
                ) {
                    navigateToHome()
                }
                MediumButton(
                    modifier = Modifier.weight(1f),
                    text = stringResource(id = R.string.announcement_creation_success_to_detail)
                ) {
                    navigateToAnnouncementDetail(id, title)
                }
            }
        }
    ) {
        Column(
            modifier = modifier
                .screenHorizonPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_success_bell),
                    contentDescription = "success"
                )
                Spacer(modifier = Modifier.height(18.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.announcement_creation_success_title),
                        style = Title4
                    )
                    Text(
                        text = stringResource(id = R.string.announcement_creation_success_content),
                        style = SubTitle1
                    )
                }
            }
        }
    }
}