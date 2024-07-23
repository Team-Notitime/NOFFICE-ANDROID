package com.easyhz.noffice.feature.announcement.screen.creation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
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
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeBasicScaffold
import com.easyhz.noffice.core.design_system.component.topBar.DetailTopBar
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Green100
import com.easyhz.noffice.core.design_system.theme.Green500
import com.easyhz.noffice.core.design_system.theme.Grey300
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.theme.Grey50
import com.easyhz.noffice.core.design_system.theme.Grey600
import com.easyhz.noffice.core.design_system.util.textField.TextFieldType
import com.easyhz.noffice.core.design_system.util.topBar.DetailTopBarMenu
import com.easyhz.noffice.feature.announcement.component.creation.ContentTextField
import com.easyhz.noffice.feature.announcement.component.creation.TitleTextField
import com.easyhz.noffice.feature.announcement.contract.creation.CreationIntent
import com.easyhz.noffice.feature.announcement.contract.creation.CreationSideEffect

@Composable
fun ContentScreen(
    modifier: Modifier = Modifier,
    viewModel: CreationViewModel = hiltViewModel(),
    navigateToUp: () -> Unit,
    navigateToPlace: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
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
        Column(modifier = modifier
            .padding(paddingValues)
            .fillMaxSize()
            .height(LocalConfiguration.current.screenHeightDp.dp)
            .screenHorizonPadding()) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    TitleTextField(
                        modifier = Modifier.padding(top = 16.dp),
                        textFieldType = TextFieldType.SINGLE,
                        value = uiState.title,
                        onChangeValue = { viewModel.postIntent(CreationIntent.ChangeTitleTextValue(it)) },
                        title = stringResource(id = R.string.announcement_creation_title_caption),
                        placeholder = stringResource(id = R.string.announcement_creation_title_placeholder),
                        singleLine = true,
                    )
                }
                item {
                    ContentTextField(
                        textFieldModifier = Modifier.height(200.dp),
                        textFieldType = TextFieldType.MULTIPLE,
                        value = uiState.content,
                        onChangeValue = { viewModel.postIntent(CreationIntent.ChangeContentTextValue(it)) },
                        title = stringResource(id = R.string.announcement_creation_content_caption),
                        placeholder = stringResource(id = R.string.announcement_creation_content_placeholder),
                        onTextLayout = {  }
                    )
                }
                items(uiState.optionState) {item ->
                    CheckButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = item.type.stringId),
                        isComplete = item.isSelected,
                        iconId = R.drawable.ic_chevron_right,
                        color = CheckButtonDefaults(
                            completeContainerColor = Green100,
                            completeContentColor = Green500,
                            completeIconColor = Green500,
                            incompleteContainerColor = Grey50,
                            incompleteContentColor = Grey600,
                            incompleteIconColor = Grey300
                        )
                    ) { viewModel.postIntent(CreationIntent.ClickOptionButton(item)) }
                }
            }
            MediumButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                text = stringResource(id = R.string.sign_up_button),
                enabled = true
            ) {
                viewModel.postIntent(CreationIntent.ClickNextButton)
            }
        }
    }
    viewModel.sideEffect.collectInSideEffectWithLifecycle {sideEffect ->
        when(sideEffect) {
            is CreationSideEffect.NavigateToUp -> { navigateToUp() }
            is CreationSideEffect.NavigateToNext -> { /*TODO 성공 화면으*/ }
            is CreationSideEffect.ScrollToBottom -> { }
            else -> { }
        }
    }
}