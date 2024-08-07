package com.easyhz.noffice.feature.organization.screen.member

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.noffice.core.common.util.collectInSideEffectWithLifecycle
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.member.MemberItem
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeBasicScaffold
import com.easyhz.noffice.core.design_system.component.topBar.DetailTopBar
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Green700
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.theme.White
import com.easyhz.noffice.core.design_system.theme.semiBold
import com.easyhz.noffice.core.design_system.util.topBar.DetailTopBarMenu
import com.easyhz.noffice.core.model.organization.member.MemberType
import com.easyhz.noffice.feature.organization.component.member.AuthorityBottomSheet
import com.easyhz.noffice.feature.organization.component.member.MemberBottomBar
import com.easyhz.noffice.feature.organization.contract.member.MemberIntent
import com.easyhz.noffice.feature.organization.contract.member.MemberSideEffect
import com.easyhz.noffice.feature.organization.util.member.MemberViewType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemberScreen(
    modifier: Modifier = Modifier,
    viewModel: MemberViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    val isEditMode = remember(uiState) { uiState.viewType == MemberViewType.EDIT }
    BackHandler {
        viewModel.postIntent(MemberIntent.NavigateToUp)
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
                    onClick = { viewModel.postIntent(MemberIntent.NavigateToUp) }
                ),
                title = stringResource(id = uiState.viewType.title),
                trailingItem = DetailTopBarMenu(
                    content = {
                        AnimatedVisibility(
                            visible = isEditMode,
                            enter = fadeIn(),
                            exit = fadeOut()
                        ) {
                            Text(
                                text = stringResource(id = R.string.organization_management_top_bar_save_button),
                                style = semiBold(18),
                                color = Green700
                            )
                        }
                    },
                    onClick = { /* 저장 */ }
                ),
            )
        },
        bottomBar = {
            MemberBottomBar(
                memberViewType = uiState.viewType,
                onClickListener = object : MemberViewType.ClickListener {
                    override fun onClickLeftButton() {
                        viewModel.postIntent(MemberIntent.ClickLeftButton)
                    }

                    override fun onClickRightButton() {
                        viewModel.postIntent(MemberIntent.ClickRightButton)
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
                    memberType = if (it <= 2) MemberType.LEADER else MemberType.MEMBER,
                    isChecked = if (isEditMode) false else null
                )
            }
        }
        if (uiState.isOpenBottomSheet) {
            AuthorityBottomSheet(
                modifier = Modifier.screenHorizonPadding(),
                sheetState = sheetState,
                memberType = uiState.authorityType,
                onDismissRequest = { viewModel.postIntent(MemberIntent.HideBottomSheet) },
                onClickAuthority = { viewModel.postIntent(MemberIntent.ClickAuthorityMemberType(it)) }
            ) {
                viewModel.postIntent(MemberIntent.ClickAuthorityButton)
            }
        }
    }

    viewModel.sideEffect.collectInSideEffectWithLifecycle {sideEffect ->
        when(sideEffect) {
            is MemberSideEffect.NavigateToUp -> { }
            is MemberSideEffect.HideBottomSheet -> {
                sheetState.hide()
                viewModel.postIntent(MemberIntent.CompleteHideBottomSheet)
            }
        }
    }
}