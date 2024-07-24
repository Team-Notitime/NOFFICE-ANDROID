package com.easyhz.noffice.navigation.announcement

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.easyhz.noffice.feature.announcement.screen.creation.ContentScreen
import com.easyhz.noffice.feature.announcement.screen.creation.CreationViewModel
import com.easyhz.noffice.feature.announcement.screen.creation.NofficeSelectionScreen
import com.easyhz.noffice.feature.announcement.screen.creation.datetime.DateTimeScreen
import com.easyhz.noffice.feature.announcement.screen.creation.place.PlaceScreen
import com.easyhz.noffice.navigation.announcement.screen.AnnouncementCreation
import com.easyhz.noffice.navigation.util.DURATION
import com.easyhz.noffice.navigation.util.sharedViewModel

internal fun NavGraphBuilder.announcementScreen(
    navController: NavController,
) {
    navigation<AnnouncementCreation>(
        startDestination = AnnouncementCreation.NofficeSelection,
        enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(DURATION)) },
        exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(DURATION)) },
        popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(DURATION)) },
        popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(DURATION)) }
    ) {
        composable<AnnouncementCreation.NofficeSelection> {
            val viewModel = it.sharedViewModel<CreationViewModel>(navController = navController)
            NofficeSelectionScreen(
                viewModel = viewModel,
                navigateToUp = navController::navigateUp,
                navigateToAnnouncementCreationContent = navController::navigateToAnnouncementCreationContent
            )
        }

        composable<AnnouncementCreation.Content> {
            val viewModel = it.sharedViewModel<CreationViewModel>(navController = navController)
            ContentScreen(
                viewModel = viewModel,
                navigateToUp = navController::navigateUp,
                navigateToDateTime = navController::navigateToDateTime,
                navigateToPlace = navController::navigateToPlace
            )
        }

        composable<AnnouncementCreation.DateTime> {
            val viewModel = it.sharedViewModel<CreationViewModel>(navController = navController)
            val args = it.toRoute<AnnouncementCreation.DateTime>()

            DateTimeScreen(
                creationViewModel = viewModel,
                date = args.date,
                time = args.time,
                navigateToUp = navController::navigateUp
            )
        }

        composable<AnnouncementCreation.Place> {
            val viewModel = it.sharedViewModel<CreationViewModel>(navController = navController)
            val args = it.toRoute<AnnouncementCreation.Place>()
            PlaceScreen(
                creationViewModel = viewModel,
                contactType = args.contactType,
                title = args.title,
                url = args.url,
                navigateToUp = navController::navigateUp
            )
        }

        composable<AnnouncementCreation.Task> {

        }

        composable<AnnouncementCreation.Remind> {

        }
    }
}
internal fun NavController.navigateToAnnouncementNofficeSelection() {
    navigate(
        route = AnnouncementCreation.NofficeSelection
    )
}

internal fun NavController.navigateToAnnouncementCreationContent() {
    navigate(
        route = AnnouncementCreation.Content
    )
}

internal fun NavController.navigateToDateTime(date: String?, time: String?) {
    navigate(
        route = AnnouncementCreation.DateTime(date, time)
    )
}

internal fun NavController.navigateToPlace(
    contactType: String?,
    title: String?,
    url: String?
) {
    navigate(
        route = AnnouncementCreation.Place(
            contactType = contactType,
            title = title,
            url = url
        )
    )
}