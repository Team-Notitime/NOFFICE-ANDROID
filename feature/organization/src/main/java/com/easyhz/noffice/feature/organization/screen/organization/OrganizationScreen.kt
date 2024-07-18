package com.easyhz.noffice.feature.organization.screen.organization

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeScaffold
import com.easyhz.noffice.core.design_system.component.topBar.HomeTopBar
import com.easyhz.noffice.feature.organization.util.OrganizationTopBarMenu

@Composable
fun OrganizationScreen(
    modifier: Modifier = Modifier,
) {
    NofficeScaffold(
        modifier = modifier,
        topBar = {
            HomeTopBar(
                tabs = enumValues<OrganizationTopBarMenu>(),
                onClickIconMenu = { }
            ) {

            }
        }
    ) {
//       Box(modifier = Modifier.fillMaxSize().padding(it)) {
//
//       }
    }
}