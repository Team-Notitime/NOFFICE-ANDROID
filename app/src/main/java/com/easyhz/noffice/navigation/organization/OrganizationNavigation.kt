package com.easyhz.noffice.navigation.organization

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import androidx.navigation.toRoute
import com.easyhz.noffice.feature.organization.screen.creation.OrganizationCreationScreen
import com.easyhz.noffice.feature.organization.screen.detail.OrganizationDetailScreen
import com.easyhz.noffice.feature.organization.screen.invitation.OrganizationInvitationScreen
import com.easyhz.noffice.feature.organization.screen.organization.OrganizationScreen
import com.easyhz.noffice.navigation.organization.screen.Organization
import com.easyhz.noffice.navigation.organization.screen.OrganizationCreation
import com.easyhz.noffice.navigation.organization.screen.OrganizationDetail
import com.easyhz.noffice.navigation.organization.screen.OrganizationInvitation

internal fun NavGraphBuilder.organizationScreen(
    modifier: Modifier,
    navigateToOrganizationDetail: (Int, String) -> Unit,
    navigateToAnnouncementDetail: (Int, String) -> Unit,
    navigateToCreation: () -> Unit,
    navigateToInvitation: (String, String)-> Unit,
    navigateToHome: () -> Unit,
    navigateToUp: () -> Unit,
) {
    composable<Organization>(
        enterTransition = { fadeIn(animationSpec = tween(700)) },
        exitTransition = { fadeOut(animationSpec = tween(700)) },
        popEnterTransition = { fadeIn(animationSpec = tween(700)) },
        popExitTransition = { fadeOut(animationSpec = tween(700)) }
    ) {
        OrganizationScreen(
            modifier = modifier,
            navigateToCreation = navigateToCreation,
            navigateToDetail = navigateToOrganizationDetail
        )
    }
    composable<OrganizationDetail> {
        val args = it.toRoute<OrganizationDetail>()
        OrganizationDetailScreen(
            organizationId = args.organizationId,
            organizationName = args.organizationName,
            navigateToUp = navigateToUp,
            navigateToAnnouncementDetail = navigateToAnnouncementDetail
        )
    }
    composable<OrganizationCreation> {
        OrganizationCreationScreen(
            navigateToInvitation = navigateToInvitation,
            navigateToUp = navigateToUp
        )
    }
    composable<OrganizationInvitation> {
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

internal fun NavController.navigateToOrganizationDetail(id: Int, name: String) {
    navigate(OrganizationDetail(organizationId = id, organizationName = name))
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