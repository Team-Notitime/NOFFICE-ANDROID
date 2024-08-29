package com.easyhz.noffice.feature.my_page.screen.detail.withdrawal

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.noffice.core.common.util.collectInSideEffectWithLifecycle
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.button.CircleCheck
import com.easyhz.noffice.core.design_system.component.button.MediumButton
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeBasicScaffold
import com.easyhz.noffice.core.design_system.component.topBar.DetailTopBar
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Green500
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.theme.Grey50
import com.easyhz.noffice.core.design_system.theme.Grey800
import com.easyhz.noffice.core.design_system.theme.SemiBold16
import com.easyhz.noffice.core.design_system.theme.SubBody14
import com.easyhz.noffice.core.design_system.theme.Title3
import com.easyhz.noffice.core.design_system.theme.White
import com.easyhz.noffice.core.design_system.util.topBar.DetailTopBarMenu
import com.easyhz.noffice.feature.my_page.component.detail.WithdrawalTypeItem
import com.easyhz.noffice.feature.my_page.contract.detail.withdrawal.WithdrawalIntent
import com.easyhz.noffice.feature.my_page.contract.detail.withdrawal.WithdrawalSideEffect
import com.easyhz.noffice.feature.my_page.util.WithdrawalType

@Composable
fun WithdrawalScreen(
    modifier: Modifier = Modifier,
    viewModel: WithdrawalViewModel = hiltViewModel(),
    navigateToUp: () -> Unit,
    navigateToLogin: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

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
                    onClick = { viewModel.postIntent(WithdrawalIntent.ClickBackButton) }
                ),
            )
        },
        bottomBar = {
            MediumButton(
                modifier = Modifier
                    .screenHorizonPadding()
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                text = stringResource(id = R.string.my_page_menu_withdrawal_button),
                enabled = uiState.isChecked
            ) { viewModel.postIntent(WithdrawalIntent.ClickWithdrawalButton) }
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = stringResource(id = R.string.my_page_menu_withdrawal), style = SemiBold16, color = Green500)
                Text(
                    text = stringResource(id = R.string.my_page_menu_withdrawal_title),
                    style = Title3,
                    lineHeight = (22 * 1.4).sp,
                    color = Grey800
                )
            }
            Spacer(
                modifier = Modifier
                    .weight(0.1f)
            )
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.ic_withdrawal),
                    contentDescription = "withdrawal"
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(White)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(text = stringResource(id = R.string.my_page_menu_withdrawal_type_title), style = SemiBold16)
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    WithdrawalType.entries.forEach {
                        WithdrawalTypeItem(type = it)
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(White)
                    .clickable { viewModel.postIntent(WithdrawalIntent.ClickConsentButton) }
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircleCheck(
                    modifier = Modifier.size(18.dp),
                    isChecked = uiState.isChecked,
                    enabled = false
                )
                Text(text = stringResource(id = R.string.my_page_menu_withdrawal_consent), style = SubBody14)
            }
            Spacer(
                modifier = Modifier
                    .weight(0.3f)
            )
        }
    }

    viewModel.sideEffect.collectInSideEffectWithLifecycle { sideEffect ->
        when(sideEffect) {
            is WithdrawalSideEffect.NavigateToUp -> { navigateToUp() }
            is WithdrawalSideEffect.NavigateToLogIn -> { navigateToLogin() }
        }
    }
}