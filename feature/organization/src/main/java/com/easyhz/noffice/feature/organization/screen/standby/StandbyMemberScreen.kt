package com.easyhz.noffice.feature.organization.screen.standby

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.easyhz.noffice.core.common.util.collectInSideEffectWithLifecycle
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.member.MemberItem
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeBasicScaffold
import com.easyhz.noffice.core.design_system.component.topBar.DetailTopBar
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.theme.White
import com.easyhz.noffice.core.design_system.util.topBar.DetailTopBarMenu
import com.easyhz.noffice.core.model.organization.member.MemberType
import com.easyhz.noffice.feature.organization.component.member.MemberBottomBar
import com.easyhz.noffice.feature.organization.contract.standby.StandbyMemberIntent
import com.easyhz.noffice.feature.organization.contract.standby.StandbyMemberSideEffect
import com.easyhz.noffice.feature.organization.util.member.MemberViewType

@Composable
fun StandbyMemberScreen(
    modifier: Modifier = Modifier,
    viewModel: StandbyMemberViewModel = hiltViewModel(),
    organizationId: Int,
    navigateToUp: () -> Unit
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
                    onClick = { viewModel.postIntent(StandbyMemberIntent.ClickBackButton) }
                ),
                title = stringResource(id = MemberViewType.STANDBY.title)
            )
        },
        bottomBar = {
            MemberBottomBar(
                memberViewType = MemberViewType.STANDBY,
                onClickListener = object : MemberViewType.ClickListener {
                    override fun onClickLeftButton() {
                        viewModel.postIntent(StandbyMemberIntent.ClickLeftButton)
                    }

                    override fun onClickRightButton() {
                        viewModel.postIntent(StandbyMemberIntent.ClickRightButton)
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .padding(paddingValues)
                .screenHorizonPadding(),
        ) {
            items(7) {
                MemberItem(
                    modifier = Modifier.padding(vertical = 4.dp),
                    name = "멤버${it + 1}",
                    imageUrl = if (it % 3 != 0) "" else "https://picsum.photos/id/${30 + it}/200/300",
                    memberType = MemberType.MEMBER,
                    isChecked = false
                )
            }
        }
    }

    viewModel.sideEffect.collectInSideEffectWithLifecycle { sideEffect ->
        when(sideEffect) {
            is StandbyMemberSideEffect.NavigateToUp -> { navigateToUp() }
        }
    }
}