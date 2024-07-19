package com.easyhz.noffice

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import com.easyhz.noffice.navigation.home.screen.Home
import com.easyhz.noffice.navigation.organization.navigateToOrganizationCreation
import com.easyhz.noffice.navigation.organization.organizationScreen
import com.easyhz.noffice.navigation.rememberNofficeNavController
import com.easyhz.noffice.navigation.sign.signScreen
import com.easyhz.noffice.navigation.util.BOTTOM_BAR_DURATION
import com.easyhz.noffice.navigation.util.BottomMenuTabs

@Composable
fun NofficeApp() {
    val nofficeNavController = rememberNofficeNavController()
    val navController = nofficeNavController.navController
    val isVisibleBottomBar = nofficeNavController.isInBottomTabs()

    val currentTab = nofficeNavController.mapRouteToTab()

    NofficeScaffold(
        floatingActionButton = {
            AnimatedVisibility(
                visible = isVisibleBottomBar,
                enter = slideInVertically(
                    initialOffsetY = { it },
                    animationSpec = tween(durationMillis = BOTTOM_BAR_DURATION)
                ),
                exit = slideOutVertically(
                    targetOffsetY = { it + 52 },
                    animationSpec = tween(durationMillis = BOTTOM_BAR_DURATION)
                )
            ) {
                HomeAddButton(
                    modifier = Modifier.offset(y = 52.dp)
                ) { }
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            AnimatedVisibility(
                visible = isVisibleBottomBar,
                enter = slideInVertically(
                    initialOffsetY = { it },
                    animationSpec = tween(durationMillis = BOTTOM_BAR_DURATION)
                ),
                exit = slideOutVertically(
                    targetOffsetY = { it },
                    animationSpec = tween(durationMillis = BOTTOM_BAR_DURATION)
                )
            ) {
                HomeBottomBar(
                    tabs = enumValues<BottomMenuTabs>(),
                    current = currentTab,
                    onClick = { nofficeNavController.navigate(it) }
                )
            }
        }
    ) {
        NavHost(
            navController = navController, startDestination = Home
        ) {
            homeScreen(modifier = Modifier.padding(it))
            organizationScreen(
                modifier = Modifier.padding(it),
                navigateToCreation = navController::navigateToOrganizationCreation
            )
            signScreen()
        }
    }
}