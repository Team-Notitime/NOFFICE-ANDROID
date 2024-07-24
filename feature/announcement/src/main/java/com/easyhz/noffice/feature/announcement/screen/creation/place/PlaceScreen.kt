package com.easyhz.noffice.feature.announcement.screen.creation.place

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.noffice.core.common.util.collectInSideEffectWithLifecycle
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.button.MediumButton
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeBasicScaffold
import com.easyhz.noffice.core.design_system.component.tab.SegmentedButton
import com.easyhz.noffice.core.design_system.component.textField.MainTextField
import com.easyhz.noffice.core.design_system.component.topBar.DetailTopBar
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.util.textField.TextFieldIcon
import com.easyhz.noffice.core.design_system.util.topBar.DetailTopBarMenu
import com.easyhz.noffice.feature.announcement.component.creation.CreationTitle
import com.easyhz.noffice.feature.announcement.contract.creation.CreationIntent
import com.easyhz.noffice.feature.announcement.contract.creation.place.ContactType
import com.easyhz.noffice.feature.announcement.contract.creation.place.PlaceIntent
import com.easyhz.noffice.feature.announcement.contract.creation.place.PlaceSideEffect
import com.easyhz.noffice.feature.announcement.screen.creation.CreationViewModel

@Composable
fun PlaceScreen(
    modifier: Modifier = Modifier,
    viewModel: PlaceViewModel = hiltViewModel(),
    creationViewModel: CreationViewModel = hiltViewModel(),
    contactType: String? = null,
    title: String? = null,
    url: String? = null,
    navigateToUp: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val focusManger = LocalFocusManager.current

    LaunchedEffect(key1 = Unit) {
        viewModel.postIntent(PlaceIntent.InitScreen(contactType, title, url))
    }

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
                    onClick = { viewModel.postIntent(PlaceIntent.ClickBackButton) }
                ),
                title = stringResource(id = R.string.announcement_creation_title),
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .screenHorizonPadding()
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            CreationTitle(
                title = stringResource(id = R.string.announcement_creation_option_place_title)
            )
            SegmentedButton(
                selectedIndex = ContactType.entries.indexOf(uiState.contactState.contactType),
                items = enumValues<ContactType>()
            ) {
                viewModel.postIntent(PlaceIntent.SelectedContactType(it))
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .onFocusChanged {
                        viewModel.postIntent(PlaceIntent.ChangedFocus(it.hasFocus))
                    }
                    .padding(top = 36.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                when (uiState.contactState.contactType) {
                    ContactType.NONE_CONTACT -> {
                        MainTextField(
                            value = uiState.contactState.title,
                            onValueChange = { viewModel.postIntent(PlaceIntent.ChangePlaceTitleTextValue(it)) },
                            title = stringResource(id = R.string.announcement_creation_option_place_none_contact_title),
                            placeholder = stringResource(id = R.string.announcement_creation_option_place_placeholder),
                            isFilled = false,
                            singleLine = true,
                            icon = TextFieldIcon.CLEAR,
                            onClickIcon = { viewModel.postIntent(PlaceIntent.ClearPlaceTitleTextValue) }
                        )
                    }
                    else -> { }
                }
                MainTextField(
                    value = uiState.contactState.url,
                    onValueChange = { viewModel.postIntent(PlaceIntent.ChangePlaceUrlTextValue(it)) },
                    title = stringResource(id = R.string.announcement_creation_option_place_caption),
                    placeholder = stringResource(id = R.string.announcement_creation_option_place_placeholder),
                    isFilled = false,
                    singleLine = true,
                    icon = TextFieldIcon.CLEAR,
                    onClickIcon = { viewModel.postIntent(PlaceIntent.ClearPlaceUrlTextValue) }
                )
            }
            MediumButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                text = stringResource(id = R.string.announcement_creation_option_button),
                enabled = true
            ) {
                viewModel.postIntent(PlaceIntent.ClickSaveButton)
            }
        }
    }

    viewModel.sideEffect.collectInSideEffectWithLifecycle {sideEffect ->
        when(sideEffect) {
            is PlaceSideEffect.NavigateToUp -> { navigateToUp() }
            is PlaceSideEffect.NavigateToNext -> {
                focusManger.clearFocus()
                creationViewModel.postIntent(CreationIntent.SaveOptionData(sideEffect.data))
                navigateToUp()
            }
            is PlaceSideEffect.ClearFocus -> { focusManger.clearFocus() }
        }
    }

}