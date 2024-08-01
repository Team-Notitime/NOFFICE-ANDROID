package com.easyhz.noffice.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.easyhz.noffice.navigation.announcement.navigateToAnnouncementNofficeSelection
import com.easyhz.noffice.navigation.home.navigateToHome
import com.easyhz.noffice.navigation.home.screen.Home
import com.easyhz.noffice.navigation.organization.navigateToOrganization
import com.easyhz.noffice.navigation.organization.screen.Organization
import com.easyhz.noffice.navigation.util.BottomMenuTabs

@Composable
internal fun rememberNofficeNavController(navController: NavHostController = rememberNavController()) =
    remember { NofficeNavController(navController = navController) }

internal class NofficeNavController(
    val navController: NavHostController
) {
    private val navBackStackEntry: NavBackStackEntry?
        @Composable get() = navController.currentBackStackEntryAsState().value

    private val routes: List<String>
        @Composable get() = remember { BottomMenuTabs.entries.map { it.qualifierName } }

    private val currentRoute: String?
        @Composable get() = navBackStackEntry?.destination?.route

    @Composable
    fun isInBottomTabs(): Boolean = currentRoute in routes

    @Composable
    fun mapRouteToTab(): BottomMenuTabs? {
        return when (currentRoute) {
            Home::class.java.name -> BottomMenuTabs.HOME
            Organization::class.java.name -> BottomMenuTabs.ORGANIZATION
            else -> null
        }
    }

    fun navigate(route: BottomMenuTabs) {
        if (route.qualifierName == navController.currentDestination?.route) return
        val navOptions = navOptions {
            popUpTo(navController.graph.id) {
                saveState = true
                inclusive = false
            }
            launchSingleTop  = true
            restoreState = true
        }
        when (route) {
            BottomMenuTabs.HOME -> navController.navigateToHome(navOptions)
            BottomMenuTabs.ADD -> navController.navigateToAnnouncementNofficeSelection()
            BottomMenuTabs.ORGANIZATION -> navController.navigateToOrganization(navOptions)
        }
    }
}