package com.easyhz.noffice.feature.organization.screen.standby

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.noffice.core.common.util.collectInSideEffectWithLifecycle
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.member.MemberItem
import com.easyhz.noffice.core.design_system.component.member.MemberItemSkeleton
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeBasicScaffold
import com.easyhz.noffice.core.design_system.component.topBar.DetailTopBar
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.theme.Grey600
import com.easyhz.noffice.core.design_system.theme.SemiBold16
import com.easyhz.noffice.core.design_system.theme.White
import com.easyhz.noffice.core.design_system.util.topBar.DetailTopBarMenu
import com.easyhz.noffice.feature.organization.component.member.MemberBottomBar
import com.easyhz.noffice.feature.organization.contract.standby.StandbyMemberIntent
import com.easyhz.noffice.feature.organization.contract.standby.StandbyMemberSideEffect
import com.easyhz.noffice.feature.organization.util.member.MemberViewType

@Composable
fun StandbyMemberScreen(
    modifier: Modifier = Modifier,
    viewModel: StandbyMemberViewModel = hiltViewModel(),
    snackBarHostState: SnackbarHostState,
    organizationId: Int,
    navigateToUp: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        viewModel.postIntent(StandbyMemberIntent.InitScreen(organizationId))
    }
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
            AnimatedVisibility(visible = uiState.memberList.isNotEmpty()) {
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
        }
    ) { paddingValues ->
        if(!uiState.isLoading && uiState.memberList.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = stringResource(id = R.string.organization_standby_member_empty),
                    style = SemiBold16,
                    color = Grey600
                )
            }
        }
        LazyColumn(
            modifier = modifier
                .padding(paddingValues)
                .screenHorizonPadding()
                .padding(horizontal = 8.dp),
        ) {
            if (uiState.isLoading) {
                items(3) {
                    MemberItemSkeleton(Modifier.padding(vertical = 4.dp))
                }
            }
            itemsIndexed(uiState.memberList, key = { _, item -> item.id }) {index, item ->
                MemberItem(
                    modifier = Modifier.padding(vertical = 4.dp),
                    member = item,
                    isEditMode = true
                ) {
                    viewModel.postIntent(StandbyMemberIntent.ClickMember(index))
                }
            }
        }
    }

    viewModel.sideEffect.collectInSideEffectWithLifecycle { sideEffect ->
        when(sideEffect) {
            is StandbyMemberSideEffect.NavigateToUp -> { navigateToUp() }
            is StandbyMemberSideEffect.ShowSnackBar -> {
                snackBarHostState.showSnackbar(
                    message = context.getString(sideEffect.stringId),
                    withDismissAction = true
                )
            }
        }
    }
}