package com.easyhz.noffice

import androidx.compose.animation.AnimatedContentTransitionScope
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
import com.easyhz.noffice.navigation.NofficeNavController
import com.easyhz.noffice.navigation.announcement.announcementGraph
import com.easyhz.noffice.navigation.announcement.navigateToAnnouncementNofficeSelection
import com.easyhz.noffice.navigation.home.homeGraph
import com.easyhz.noffice.navigation.home.screen.Splash
import com.easyhz.noffice.navigation.my_page.myPageGraph
import com.easyhz.noffice.navigation.my_page.navigateToConsent
import com.easyhz.noffice.navigation.my_page.navigateToNotice
import com.easyhz.noffice.navigation.my_page.navigateToNoticeDetail
import com.easyhz.noffice.navigation.my_page.navigateToWithdrawal
import com.easyhz.noffice.navigation.organization.organizationGraph
import com.easyhz.noffice.navigation.sign.navigateToLogIn
import com.easyhz.noffice.navigation.sign.signGraph
import com.easyhz.noffice.navigation.util.BOTTOM_BAR_DURATION
import com.easyhz.noffice.navigation.util.BottomMenuTabs
import com.easyhz.noffice.navigation.util.DURATION

@Composable
internal fun NofficeApp(
    nofficeNavController: NofficeNavController
) {
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
                ) { navController.navigateToAnnouncementNofficeSelection() }
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
            navController = navController,
            startDestination = Splash,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start, tween(
                        DURATION
                    )
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start, tween(
                        DURATION
                    )
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.End, tween(
                        DURATION
                    )
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.End, tween(
                        DURATION
                    )
                )
            }
        ) {
            homeGraph(
                modifier = Modifier.padding(it),
                navController = navController
            )
            organizationGraph(
                modifier = Modifier.padding(it),
                navController = navController,
            )
            signGraph(
                navController = navController
            )
            announcementGraph(
                navController = navController,
            )
            myPageGraph(
                navigateToUp = navController::navigateUp,
                navigateToNotice = navController::navigateToNotice,
                navigateToNoticeDetail = navController::navigateToNoticeDetail,
                navigateToConsent = navController::navigateToConsent,
                navigateToWithdrawal = navController::navigateToWithdrawal,
                navigateToLogIn = navController::navigateToLogIn
            )
        }
    }
}