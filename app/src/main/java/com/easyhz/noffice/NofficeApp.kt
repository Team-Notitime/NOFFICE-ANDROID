package com.easyhz.noffice

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import com.easyhz.noffice.core.design_system.component.bottomBar.HomeBottomBar
import com.easyhz.noffice.core.design_system.component.button.HomeAddButton
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeScaffold
import com.easyhz.noffice.navigation.home.homeScreen
import com.easyhz.noffice.navigation.organization.organizationScreen
import com.easyhz.noffice.navigation.rememberNofficeNavController
import com.easyhz.noffice.navigation.sign.screen.SignUp
import com.easyhz.noffice.navigation.sign.signScreen
import com.easyhz.noffice.navigation.util.BottomMenuTabs

@Composable
fun NofficeApp() {
    val nofficeNavController = rememberNofficeNavController()
    val navController = nofficeNavController.navController
    val isVisibleBottomBar = nofficeNavController.isInBottomTabs()

    val currentTab = nofficeNavController.mapRouteToTab()

    NofficeScaffold(
        floatingActionButton = {
            if(isVisibleBottomBar) {
                HomeAddButton(
                    modifier = Modifier.offset(y = 52.dp)
                ) { }
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            if(isVisibleBottomBar) {
                HomeBottomBar(
                    tabs = enumValues<BottomMenuTabs>(),
                    current = currentTab,
                    onClick = { nofficeNavController.navigate(it) }
                )
            }
        }
    ) {
        NavHost(
            navController = navController, startDestination = SignUp
        ) {
            homeScreen(modifier = Modifier.padding(it))
            organizationScreen(modifier = Modifier.padding(it))
            signScreen()
        }
    }
}