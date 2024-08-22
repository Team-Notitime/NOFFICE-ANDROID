package com.easyhz.noffice.navigation.announcement

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import androidx.navigation.navOptions
import androidx.navigation.toRoute
import com.easyhz.noffice.core.model.announcement.param.AnnouncementParam
import com.easyhz.noffice.feature.announcement.screen.creation.ContentScreen
import com.easyhz.noffice.feature.announcement.screen.creation.CreationViewModel
import com.easyhz.noffice.feature.announcement.screen.creation.datetime.DateTimeScreen
import com.easyhz.noffice.feature.announcement.screen.creation.place.PlaceScreen
import com.easyhz.noffice.feature.announcement.screen.creation.promotion.PromotionScreen
import com.easyhz.noffice.feature.announcement.screen.creation.remind.CustomRemindScreen
import com.easyhz.noffice.feature.announcement.screen.creation.remind.RemindScreen
import com.easyhz.noffice.feature.announcement.screen.creation.remind.RemindViewModel
import com.easyhz.noffice.feature.announcement.screen.creation.selection.NofficeSelectionScreen
import com.easyhz.noffice.feature.announcement.screen.creation.task.TaskScreen
import com.easyhz.noffice.feature.announcement.screen.detail.AnnouncementDetailScreen
import com.easyhz.noffice.feature.announcement.screen.success.SuccessScreen
import com.easyhz.noffice.navigation.announcement.screen.AnnouncementCreation
import com.easyhz.noffice.navigation.announcement.screen.AnnouncementCreation.Promotion.Companion.decode
import com.easyhz.noffice.navigation.announcement.screen.AnnouncementCreation.Promotion.Companion.encode
import com.easyhz.noffice.navigation.announcement.screen.AnnouncementDetail
import com.easyhz.noffice.navigation.announcement.screen.AnnouncementSuccess
import com.easyhz.noffice.navigation.home.navigateToHome
import com.easyhz.noffice.navigation.home.screen.Home
import com.easyhz.noffice.navigation.util.sharedViewModel

internal fun NavGraphBuilder.announcementGraph(
    snackBarHostState: SnackbarHostState,
    navController: NavController,
) {
    composable<AnnouncementDetail>(
        deepLinks =  listOf(navDeepLink {
            uriPattern = "noffice://announcement?id={id}&organizationId={organizationId}&title={title}"
        })
    ) {
        val args = it.toRoute<AnnouncementDetail>()
        AnnouncementDetailScreen(
            organizationId = args.organizationId,
            id = args.id,
            title = args.title,
            navigateToUp = navController::navigateUp
        )
    }
    navigation<AnnouncementCreation>(
        startDestination = AnnouncementCreation.NofficeSelection,
    ) {
        composable<AnnouncementCreation.NofficeSelection> {
            NofficeSelectionScreen(
                navigateToUp = navController::navigateUp,
                navigateToAnnouncementCreationContent = navController::navigateToAnnouncementCreationContent
            )
        }

        composable<AnnouncementCreation.Content> {
            val viewModel = it.sharedViewModel<CreationViewModel>(navController = navController)
            val args = it.toRoute<AnnouncementCreation.Content>()
            ContentScreen(
                viewModel = viewModel,
                organizationId = args.organizationId,
                navigateToUp = navController::navigateUp,
                navigateToDateTime = navController::navigateToDateTime,
                navigateToPlace = navController::navigateToPlace,
                navigateToTask = navController::navigateToTask,
                navigateToRemind = navController::navigateToRemind,
                navigateToPromotion = navController::navigateToPromotion
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
            val viewModel = it.sharedViewModel<CreationViewModel>(navController = navController)
            val args = it.toRoute<AnnouncementCreation.Task>()
            TaskScreen(
                creationViewModel = viewModel,
                taskList = args.taskList,
                navigateToUp = navController::navigateUp
            )
        }

        composable<AnnouncementCreation.Remind> {
            val viewModel = it.sharedViewModel<RemindViewModel>(navController = navController)
            val creationViewModel =
                it.sharedViewModel<CreationViewModel>(navController = navController)
            val args = it.toRoute<AnnouncementCreation.Remind>()
            RemindScreen(
                viewModel = viewModel,
                creationViewModel = creationViewModel,
                selectRemind = args.remindList,
                isSelectedDateTime = args.isSelectedDateTime,
                navigateToCustomRemind = navController::navigateToCustomRemind,
                navigateToUp = navController::navigateUp
            )
        }

        composable<AnnouncementCreation.CustomRemind> {
            val viewModel = it.sharedViewModel<RemindViewModel>(navController = navController)
            CustomRemindScreen(
                remindViewModel = viewModel,
                navigateToUp = navController::navigateUp
            )
        }
        composable<AnnouncementCreation.Promotion>(
            typeMap = AnnouncementCreation.Promotion.typeMap
        ) {
            val navOptions = navOptions {
                popUpTo(Home) {
                    inclusive = false
                }
            }
            val args = it.toRoute<AnnouncementCreation.Promotion>()
            PromotionScreen(
                param = args.announcementParam.decode(),
                snackBarHostState = snackBarHostState,
                navigateToUp = navController::navigateUp,
                navigateToSuccess = { organizationId, id, title ->
                    navController.navigateToSuccess(
                        organizationId,
                        id,
                        title,
                        navOptions
                    )
                }
            )
        }
    }
    composable<AnnouncementSuccess> {
        val args = it.toRoute<AnnouncementSuccess>()
        val navOptions = navOptions {
            popUpTo(Home) {
                inclusive = false
            }
        }
        SuccessScreen(
            organizationId = args.organizationId,
            id = args.announcementId,
            title = args.title,
            navigateToHome = { navController.navigateToHome(navOptions) },
            navigateToAnnouncementDetail = { organizationId, id, title -> navController.navigateToAnnouncementDetail(organizationId, id, title, navOptions)}
        )
    }
}

internal fun NavController.navigateToAnnouncementDetail(
    organizationId: Int,
    id: Int,
    title: String,
    navOptions: NavOptions? = null
) {
    navigate(route = AnnouncementDetail(organizationId = organizationId, id, title), navOptions)
}

internal fun NavController.navigateToAnnouncementNofficeSelection() {
    navigate(
        route = AnnouncementCreation.NofficeSelection
    )
}

internal fun NavController.navigateToAnnouncementCreationContent(organizationId: Int) {
    navigate(
        route = AnnouncementCreation.Content(organizationId)
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

internal fun NavController.navigateToRemind(
    remindList: List<String>?,
    isSelectedDateTime: Boolean
) {
    navigate(
        route = AnnouncementCreation.Remind(
            remindList = remindList,
            isSelectedDateTime = isSelectedDateTime
        )
    )
}

internal fun NavController.navigateToCustomRemind() {
    navigate(
        route = AnnouncementCreation.CustomRemind
    )
}

internal fun NavController.navigateToPromotion(param: AnnouncementParam) {
    navigate(route = AnnouncementCreation.Promotion(param.encode()))
}

internal fun NavController.navigateToSuccess(
    organizationId: Int,
    id: Int,
    title: String,
    navOptions: NavOptions? = null
) {
    navigate(route = AnnouncementSuccess(organizationId, id, title), navOptions)
}