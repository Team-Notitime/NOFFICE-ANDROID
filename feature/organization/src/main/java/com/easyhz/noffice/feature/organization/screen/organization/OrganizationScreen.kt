package com.easyhz.noffice.feature.organization.screen.organization

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.noffice.core.common.util.collectInSideEffectWithLifecycle
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.button.IconMediumButton
import com.easyhz.noffice.core.design_system.component.exception.ExceptionView
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeScaffold
import com.easyhz.noffice.core.design_system.component.topBar.HomeTopBar
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.util.exception.ExceptionType
import com.easyhz.noffice.feature.organization.component.organization.OrganizationItem
import com.easyhz.noffice.feature.organization.contract.organization.OrganizationIntent
import com.easyhz.noffice.feature.organization.contract.organization.OrganizationSideEffect
import com.easyhz.noffice.feature.organization.util.OrganizationTopBarMenu

@Composable
fun OrganizationScreen(
    modifier: Modifier = Modifier,
    viewModel: OrganizationViewModel = hiltViewModel(),
    navigateToCreation: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    NofficeScaffold(
        topBar = {
            HomeTopBar(
                tabs = enumValues<OrganizationTopBarMenu>(),
                onClickIconMenu = { }
            ) {

            }
        }
    ) {
        if(uiState.organizationList.isEmpty()) {
            ExceptionView(
                modifier = Modifier.fillMaxSize(),
                type = ExceptionType.NO_GROUP
            )
        }
        Column(
            modifier = modifier
                .padding(top = it.calculateTopPadding())
                .screenHorizonPadding()
        ) {
            IconMediumButton(
                modifier = Modifier.padding(vertical = 18.dp),
                text = stringResource(id = R.string.organization_new),
                iconId = R.drawable.ic_plus
            ) {
                viewModel.postIntent(OrganizationIntent.ClickOrganizationCreation)
            }
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(uiState.organizationList) { item ->
                    OrganizationItem(
                        modifier = Modifier.fillMaxWidth(),
                        organizationName = item,
                        imageUrl = if(item.length == 2) "" else "https://picsum.photos/id/${item}/200/300"
                    ) {

                    }
                }
            }

        }
    }

    viewModel.sideEffect.collectInSideEffectWithLifecycle {sideEffect ->
        when(sideEffect) {
            is OrganizationSideEffect.NavigateToCreation -> { navigateToCreation() }
        }
    }
}