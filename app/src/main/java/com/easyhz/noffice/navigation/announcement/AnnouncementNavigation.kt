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
import com.easyhz.noffice.feature.announcement.screen.creation.selection.NofficeSelectionScreen
import com.easyhz.noffice.feature.announcement.screen.creation.datetime.DateTimeScreen
import com.easyhz.noffice.feature.announcement.screen.creation.place.PlaceScreen
import com.easyhz.noffice.feature.announcement.screen.creation.remind.RemindScreen
import com.easyhz.noffice.navigation.announcement.screen.AnnouncementCreation
import com.easyhz.noffice.feature.announcement.screen.creation.task.TaskScreen
import com.easyhz.noffice.feature.announcement.screen.detail.AnnouncementDetailScreen
import com.easyhz.noffice.navigation.announcement.screen.AnnouncementDetail
import com.easyhz.noffice.navigation.util.DURATION
import com.easyhz.noffice.navigation.util.sharedViewModel

internal fun NavGraphBuilder.announcementScreen(
    navController: NavController,
) {
    composable<AnnouncementDetail>(
        enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(DURATION)) },
        exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(DURATION)) },
        popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(DURATION)) },
        popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(DURATION)) }
    ) {
        val args = it.toRoute<AnnouncementDetail>()
        AnnouncementDetailScreen(
            id = args.id,
            title = args.title,
            navigateToUp = navController::navigateUp
        )
    }
    navigation<AnnouncementCreation>(
        startDestination = AnnouncementCreation.NofficeSelection,
        enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(DURATION)) },
        exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(DURATION)) },
        popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(DURATION)) },
        popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(DURATION)) }
    ) {
        composable<AnnouncementCreation.NofficeSelection> {
            NofficeSelectionScreen(
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
                navigateToPlace = navController::navigateToPlace,
                navigateToTask = navController::navigateToTask,
                navigateToRemind = navController::navigateToRemind
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
            val viewModel = it.sharedViewModel<CreationViewModel>(navController =  navController)
            val args = it.toRoute<AnnouncementCreation.Task>()
            TaskScreen(
                creationViewModel = viewModel,
                taskList = args.taskList,
                navigateToUp = navController::navigateUp
            )
        }

        composable<AnnouncementCreation.Remind> {
            val viewModel = it.sharedViewModel<CreationViewModel>(navController = navController)
            val args = it.toRoute<AnnouncementCreation.Remind>()
            RemindScreen(
                creationViewModel = viewModel,
                selectRemind = args.remindList,
                navigateToUp = navController::navigateUp
            )
        }
    }
}

internal fun NavController.navigateToAnnouncementDetail(
    id: Int,
    title: String
) {
    navigate(route = AnnouncementDetail(id, title))
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

internal fun NavController.navigateToTask(taskList: List<String>?) {
    navigate(
        route = AnnouncementCreation.Task(taskList = taskList)
    )
}
internal fun NavController.navigateToRemind(remindList: List<String>?) {
    navigate(
        route = AnnouncementCreation.Remind(remindList = remindList)
    )
}