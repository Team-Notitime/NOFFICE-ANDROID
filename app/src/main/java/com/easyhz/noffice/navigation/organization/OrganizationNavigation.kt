package com.easyhz.noffice.navigation.organization

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import androidx.navigation.toRoute
import com.easyhz.noffice.feature.organization.screen.creation.OrganizationCreationScreen
import com.easyhz.noffice.feature.organization.screen.invitation.OrganizationInvitationScreen
import com.easyhz.noffice.feature.organization.screen.organization.OrganizationScreen
import com.easyhz.noffice.navigation.organization.screen.Organization
import com.easyhz.noffice.navigation.organization.screen.OrganizationCreation
import com.easyhz.noffice.navigation.organization.screen.OrganizationInvitation
import com.easyhz.noffice.navigation.util.DURATION

internal fun NavGraphBuilder.organizationScreen(
    modifier: Modifier,
    navigateToCreation: () -> Unit,
    navigateToInvitation: (String, String)-> Unit,
    navigateToHome: () -> Unit,
    navigateToUp: () -> Unit,
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
        OrganizationCreationScreen(
            navigateToInvitation = navigateToInvitation,
            navigateToUp = navigateToUp
        )
    }
    composable<OrganizationInvitation>(
        enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(DURATION)) },
        exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(DURATION)) },
        popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(DURATION)) },
        popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(DURATION)) }
    ) {
        val args = it.toRoute<OrganizationInvitation>()
        OrganizationInvitationScreen(
            invitationUrl = args.invitationUrl,
            imageUrl = args.imageUrl,
            navigateToHome = navigateToHome
        )
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

internal fun NavController.navigateToOrganizationInvitation(invitationUrl: String, imageUrl: String) {
    val navOptions = navOptions {
        popUpTo(OrganizationCreation) { inclusive = true }
    }
    navigate(OrganizationInvitation(invitationUrl = invitationUrl, imageUrl = imageUrl), navOptions)
}