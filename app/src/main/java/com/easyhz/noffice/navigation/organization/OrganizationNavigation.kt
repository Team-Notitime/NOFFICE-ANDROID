package com.easyhz.noffice.navigation.organization

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.easyhz.noffice.feature.organization.screen.creation.OrganizationCreationScreen
import com.easyhz.noffice.feature.organization.screen.organization.OrganizationScreen
import com.easyhz.noffice.navigation.organization.screen.Organization
import com.easyhz.noffice.navigation.organization.screen.OrganizationCreation
import com.easyhz.noffice.navigation.util.DURATION

internal fun NavGraphBuilder.organizationScreen(
    modifier: Modifier,
    navigateToCreation: () -> Unit
) {
    composable<Organization> {
        OrganizationScreen(
            modifier = modifier,
            navigateToCreation = navigateToCreation
        )
    }
    composable<OrganizationCreation>(
        enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(DURATION)) },
        exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(DURATION)) },
        popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(DURATION)) },
        popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(DURATION)) }
    ) {
        OrganizationCreationScreen()
    }
}

internal fun NavController.navigateToOrganization(navOptions: NavOptions) {
    navigate(
        route = Organization,
        navOptions = navOptions
    )
}

internal fun NavController.navigateToOrganizationCreation() {
    navigate(OrganizationCreation)
}