package com.easyhz.noffice.feature.organization

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeScaffold
import com.easyhz.noffice.core.design_system.component.topBar.HomeTopBar
import com.easyhz.noffice.core.design_system.util.topBar.TopBarMenu

@Composable
fun OrganizationScreen() {
    NofficeScaffold(
        topBar = {
            HomeTopBar(
                tabs = enumValues<OrganizationTopBarMenu>(),
                onClickIconMenu = { }
            ) {

            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(it)) {
            Text(text = "organization", modifier = Modifier.align(Alignment.Center))
        }
    }
}

enum class OrganizationTopBarMenu : TopBarMenu {
    ORGANIZATION {
        override val label: String
            get() = "나의 그룹"
    }
}