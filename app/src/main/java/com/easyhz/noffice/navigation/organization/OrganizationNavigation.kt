package com.easyhz.noffice.navigation.organization

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import androidx.navigation.toRoute
import com.easyhz.noffice.core.model.organization.OrganizationInformation
import com.easyhz.noffice.core.model.organization.OrganizationSignUpInformation
import com.easyhz.noffice.feature.organization.screen.creation.OrganizationCreationScreen
import com.easyhz.noffice.feature.organization.screen.detail.OrganizationDetailScreen
import com.easyhz.noffice.feature.organization.screen.invitation.OrganizationInvitationScreen
import com.easyhz.noffice.feature.organization.screen.join.OrganizationJoinScreen
import com.easyhz.noffice.feature.organization.screen.management.OrganizationManagementScreen
import com.easyhz.noffice.feature.organization.screen.member.MemberScreen
import com.easyhz.noffice.feature.organization.screen.organization.OrganizationScreen
import com.easyhz.noffice.feature.organization.screen.standby.StandbyMemberScreen
import com.easyhz.noffice.navigation.announcement.navigateToAnnouncementDetail
import com.easyhz.noffice.navigation.home.navigateToHome
import com.easyhz.noffice.navigation.my_page.navigateToMyPage
import com.easyhz.noffice.navigation.notification.navigateToNotification
import com.easyhz.noffice.navigation.organization.screen.MemberManagement
import com.easyhz.noffice.navigation.organization.screen.Organization
import com.easyhz.noffice.navigation.organization.screen.OrganizationCreation
import com.easyhz.noffice.navigation.organization.screen.OrganizationDetail
import com.easyhz.noffice.navigation.organization.screen.OrganizationInvitation
import com.easyhz.noffice.navigation.organization.screen.OrganizationJoin
import com.easyhz.noffice.navigation.organization.screen.OrganizationJoin.Companion.decode
import com.easyhz.noffice.navigation.organization.screen.OrganizationJoin.Companion.encode
import com.easyhz.noffice.navigation.organization.screen.OrganizationManagement
import com.easyhz.noffice.navigation.organization.screen.OrganizationManagement.Companion.decode
import com.easyhz.noffice.navigation.organization.screen.OrganizationManagement.Companion.encode
import com.easyhz.noffice.navigation.organization.screen.StandbyMember

internal fun NavGraphBuilder.organizationGraph(
    modifier: Modifier,
    navController: NavController,
    snackBarHostState: SnackbarHostState,
) {
    composable<Organization>(
        enterTransition = { fadeIn(animationSpec = tween(700)) },
        exitTransition = { fadeOut(animationSpec = tween(700)) },
        popEnterTransition = { fadeIn(animationSpec = tween(700)) },
        popExitTransition = { fadeOut(animationSpec = tween(700)) }
    ) {
        OrganizationScreen(
            modifier = modifier,
            navigateToMyPage = navController::navigateToMyPage,
            navigateToCreation = navController::navigateToOrganizationCreation,
            navigateToDetail = navController::navigateToOrganizationDetail,
            navigateToNotification = navController::navigateToNotification,
        )
    }
    composable<OrganizationDetail> {
        val args = it.toRoute<OrganizationDetail>()
        OrganizationDetailScreen(
            organizationId = args.organizationId,
            organizationName = args.organizationName,
            snackBarHostState = snackBarHostState,
            navigateToUp = navController::navigateUp,
            navigateToAnnouncementDetail = navController::navigateToAnnouncementDetail,
            navigateToStandbyMember = navController::navigateToStandbyMember,
            navigateToOrganizationManagement = navController::navigateToOrganizationManagement
        )
    }
    composable<OrganizationManagement>(
        typeMap = OrganizationManagement.typeMap
    ) {
        val args = it.toRoute<OrganizationManagement>()
        OrganizationManagementScreen(
            organizationInformation = args.organizationInformation.decode(),
            snackBarHostState = snackBarHostState,
            navigateToUp = navController::navigateUp,
            navigateToMemberManagement = navController::navigateToMemberManagement,
        )
    }
    composable<MemberManagement> {
        val args = it.toRoute<MemberManagement>()
        MemberScreen(
            snackBarHostState = snackBarHostState,
            organizationId = args.organizationId,
            imageUrl = args.imageUrl,
            navigateToUp = navController::navigateUp,
            navigateToInvitation = navController::navigateToOrganizationInvitation
        )
    }
    composable<OrganizationCreation> {
        OrganizationCreationScreen(
            snackBarHostState = snackBarHostState,
            navigateToInvitation = navController::navigateToOrganizationInvitation,
            navigateToUp = navController::navigateUp,
        )
    }
    composable<OrganizationInvitation> {
        val args = it.toRoute<OrganizationInvitation>()
        val navOptions = navOptions {
            popUpTo(navController.graph.id) {
                inclusive = true
            }
        }
        OrganizationInvitationScreen(
            snackBarHostState = snackBarHostState,
            invitationUrl = args.invitationUrl,
            imageUrl = args.imageUrl ?: "",
            isCreation = args.isCreation,
            navigateToHome = { navController.navigateToHome(navOptions) }
        )
    }
    composable<StandbyMember> {
        val args = it.toRoute<StandbyMember>()
        StandbyMemberScreen(
            organizationId = args.organizationId,
            snackBarHostState = snackBarHostState,
            navigateToUp = navController::navigateUp
        )
    }

    composable<OrganizationJoin>(
        typeMap = OrganizationJoin.typeMap
    ) {
        val args = it.toRoute<OrganizationJoin>()
        OrganizationJoinScreen(
            organizationSignUpInformation = args.organizationSignUpInformation.decode(),
            snackBarHostState = snackBarHostState,
            navigateToUp = navController::navigateUp
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

internal fun NavController.navigateToOrganizationManagement(
    information: OrganizationInformation,
) {
    navigate(OrganizationManagement(organizationInformation = information.encode()))
}

internal fun NavController.navigateToMemberManagement(id: Int, imageUrl: String?) {
    navigate(MemberManagement(id, imageUrl))
}

internal fun NavController.navigateToOrganizationCreation() {
    navigate(OrganizationCreation)
}

internal fun NavController.navigateToOrganizationInvitation(
    invitationUrl: String,
    imageUrl: String?,
    isCreation: Boolean = true,
) {
    val navOptions = navOptions {
        popUpTo(OrganizationCreation) { inclusive = true }
    }
    navigate(OrganizationInvitation(invitationUrl = invitationUrl, imageUrl = imageUrl, isCreation = isCreation), navOptions)
}

internal fun NavController.navigateToStandbyMember(id: Int) {
    navigate(StandbyMember(id))
}

internal fun NavController.navigateToOrganizationJoin(
    organizationSignUpInformation: OrganizationSignUpInformation,
    navOptions: NavOptions? = null
) {
    navigate(OrganizationJoin(organizationSignUpInformation.encode()), navOptions)
}