package com.easyhz.noffice.feature.announcement.screen.creation

import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.noffice.core.common.util.collectInSideEffectWithLifecycle
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.button.CheckButton
import com.easyhz.noffice.core.design_system.component.button.CheckButtonDefaults
import com.easyhz.noffice.core.design_system.component.button.MediumButton
import com.easyhz.noffice.core.design_system.component.loading.LoadingScreenProvider
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeBasicScaffold
import com.easyhz.noffice.core.design_system.component.topBar.DetailTopBar
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Green100
import com.easyhz.noffice.core.design_system.theme.Green700
import com.easyhz.noffice.core.design_system.theme.Grey300
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.theme.Grey50
import com.easyhz.noffice.core.design_system.theme.Grey600
import com.easyhz.noffice.core.design_system.util.textField.TextFieldType
import com.easyhz.noffice.core.design_system.util.topBar.DetailTopBarMenu
import com.easyhz.noffice.core.model.announcement.param.AnnouncementParam
import com.easyhz.noffice.feature.announcement.component.creation.ContentTextField
import com.easyhz.noffice.feature.announcement.component.creation.TitleTextField
import com.easyhz.noffice.feature.announcement.contract.creation.CreationIntent
import com.easyhz.noffice.feature.announcement.contract.creation.CreationSideEffect
import kotlinx.coroutines.launch

// TODO: 키보드 비활성화 되면 focus clear
@Composable
fun ContentScreen(
    modifier: Modifier = Modifier,
    viewModel: CreationViewModel = hiltViewModel(),
    organizationId: Int,
    navigateToUp: () -> Unit,
    navigateToDateTime: (String?, String?) -> Unit,
    navigateToPlace: (String?, String?, String?) -> Unit,
    navigateToTask: (List<String>?) -> Unit,
    navigateToRemind: (List<String>?, Boolean) -> Unit,
    navigateToPromotion: (AnnouncementParam) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current
    val halfHeight = LocalConfiguration.current.screenHeightDp / 2
    val view = LocalView.current
    val density = LocalDensity.current

    val paddingHeight = remember(density) { with(density) { 32.dp.toPx().toInt() } }

    LaunchedEffect(key1 = Unit) {
        viewModel.postIntent(CreationIntent.InitScreen(organizationId))
    }

    LaunchedEffect(key1 = uiState.absoluteCursorY, key2 = uiState.isFocused) {
        val targetScroll = uiState.absoluteCursorY - halfHeight - paddingHeight

        val shouldAnimateScroll = !uiState.isMoved &&
                (uiState.isFocused || scrollState.maxValue >= halfHeight) &&
                scrollState.value != targetScroll

        if (shouldAnimateScroll) {
            scrollState.animateScrollTo(targetScroll)
        }
    }
    LoadingScreenProvider(isLoading = false) {
        NofficeBasicScaffold(
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
                        onClick = { viewModel.postIntent(CreationIntent.ClickBackButton) }
                    ),
                    title = stringResource(id = R.string.announcement_creation_title),
                )
            }
        ) { paddingValues ->
            Column(
                modifier = modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .height(LocalConfiguration.current.screenHeightDp.dp)
                    .screenHorizonPadding()
            ) {
                Column(
                    modifier = Modifier
                        .verticalScroll(scrollState)
                        .weight(1f)
                        .pointerInput(Unit) {
                            detectVerticalDragGestures(
                                onDragStart = {
                                    focusManager.clearFocus()
                                }
                            ) { change, dragAmount -> // FIXME
                                change.consume()
                                coroutineScope.launch {
                                    scrollState.scrollBy(-dragAmount)
                                }
                            }
                        },
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    TitleTextField(
                        modifier = Modifier.padding(top = 16.dp, bottom = 4.dp),
                        textFieldType = TextFieldType.SINGLE,
                        value = uiState.title,
                        onChangeValue = { viewModel.postIntent(CreationIntent.ChangeTitleTextValue(it)) },
                        title = stringResource(id = R.string.announcement_creation_title_caption),
                        placeholder = stringResource(id = R.string.announcement_creation_title_placeholder),
                        singleLine = true,
                    )
                    ContentTextField(
                        textFieldModifier = Modifier
                            .onGloballyPositioned { _ ->
                                viewModel.postIntent(CreationIntent.GloballyPositioned(view))
                            }
                            .onFocusEvent {
                                viewModel.postIntent(CreationIntent.ChangedFocus(it.hasFocus))
                            },
                        textFieldType = TextFieldType.MULTIPLE,
                        value = uiState.content,
                        onChangeValue = {
                            viewModel.postIntent(CreationIntent.ChangeContentTextValue(it))
                        },
                        title = stringResource(id = R.string.announcement_creation_content_caption),
                        placeholder = stringResource(id = R.string.announcement_creation_content_placeholder),
                        onTextLayout = { result ->
                            viewModel.postIntent(CreationIntent.SetLayoutResult(result))
                        }
                    )
                    Spacer(
                        modifier = Modifier
                            .height(12.dp)
                            .fillMaxWidth()
                    )
                    uiState.optionState.forEach { (option, item) ->
                        CheckButton(
                            modifier = Modifier
                                .height(42.dp)
                                .fillMaxWidth(),
                            text = stringResource(id = option.stringId),
                            isComplete = item.selected,
                            iconId = R.drawable.ic_chevron_right,
                            verticalPadding = 8.dp,
                            color = CheckButtonDefaults(
                                completeContainerColor = Green100,
                                completeContentColor = Green700,
                                completeIconColor = Green700,
                                incompleteContainerColor = Grey50,
                                incompleteContentColor = Grey600,
                                incompleteIconColor = Grey300
                            )
                        ) { viewModel.postIntent(CreationIntent.ClickOptionButton(option)) }
                    }
                }
                MediumButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    text = stringResource(id = R.string.next_button),
                    enabled = uiState.enabledButton
                ) {
                    viewModel.postIntent(CreationIntent.ClickNextButton)
                }
            }
        }
    }
    viewModel.sideEffect.collectInSideEffectWithLifecycle { sideEffect ->
        when (sideEffect) {
            is CreationSideEffect.NavigateToUp -> { navigateToUp() }
            is CreationSideEffect.NavigateToNext -> { navigateToPromotion(sideEffect.param) }
            is CreationSideEffect.NavigateToDateTime -> { navigateToDateTime(sideEffect.date, sideEffect.time) }
            is CreationSideEffect.NavigateToPlace -> { navigateToPlace(sideEffect.contactType, sideEffect.title, sideEffect.url) }
            is CreationSideEffect.NavigateToTask -> { navigateToTask(sideEffect.taskList ?: emptyList()) }
            is CreationSideEffect.NavigateToRemind -> { navigateToRemind(sideEffect.remindList ?: emptyList(), sideEffect.isSelectedDateTime) }
            is CreationSideEffect.ScrollToBottom -> { }
        }
    }
}