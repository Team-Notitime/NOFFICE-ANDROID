package com.easyhz.noffice.navigation.announcement

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.easyhz.noffice.feature.announcement.screen.creation.ContentScreen
import com.easyhz.noffice.feature.announcement.screen.creation.CreationViewModel
import com.easyhz.noffice.feature.announcement.screen.creation.NofficeSelectionScreen
import com.easyhz.noffice.feature.announcement.screen.creation.PlaceScreen
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
                navigateToPlace = navController::navigateToPlace
            )
        }

        composable<AnnouncementCreation.DateTime> {  }

        composable<AnnouncementCreation.Place> {
            val viewModel = it.sharedViewModel<CreationViewModel>(navController = navController)
            PlaceScreen(
                viewModel = viewModel,
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

internal fun NavController.navigateToPlace() {
    navigate(
        route = AnnouncementCreation.Place
    )
}