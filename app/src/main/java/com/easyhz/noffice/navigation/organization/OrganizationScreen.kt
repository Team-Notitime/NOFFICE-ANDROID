package com.easyhz.noffice.navigation.organization

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.easyhz.noffice.feature.organization.OrganizationScreen
import com.easyhz.noffice.navigation.organization.screen.Organization

internal fun NavGraphBuilder.organizationScreen() {
    composable<Organization> {
        OrganizationScreen()
    }
}

internal fun NavController.navigateToOrganization(navOptions: NavOptions) {
    navigate(
        route = Organization,
        navOptions = navOptions
    )
}