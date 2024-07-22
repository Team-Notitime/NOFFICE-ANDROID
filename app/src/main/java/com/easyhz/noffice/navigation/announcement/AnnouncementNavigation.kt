package com.easyhz.noffice.navigation.announcement

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.easyhz.noffice.feature.announcement.screen.creation.ContentScreen
import com.easyhz.noffice.feature.announcement.screen.creation.NofficeSelectionScreen
import com.easyhz.noffice.navigation.announcement.screen.AnnouncementCreation

internal fun NavGraphBuilder.announcementScreen(
    modifier: Modifier = Modifier,
    navigateToAnnouncementCreationContent: () -> Unit
) {
    navigation<AnnouncementCreation>(
        startDestination = AnnouncementCreation.NofficeSelection,
    ) {
        composable<AnnouncementCreation.NofficeSelection> {
            NofficeSelectionScreen(
                navigateToAnnouncementCreationContent = navigateToAnnouncementCreationContent
            )
        }

        composable<AnnouncementCreation.Content> {
            ContentScreen()
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