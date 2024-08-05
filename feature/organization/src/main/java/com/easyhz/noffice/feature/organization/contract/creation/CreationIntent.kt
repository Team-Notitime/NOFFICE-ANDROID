package com.easyhz.noffice.feature.organization.contract.creation

import android.net.Uri
import com.easyhz.noffice.core.common.base.UiIntent
import com.easyhz.noffice.feature.organization.util.creation.BottomSheetItem
import java.time.LocalDate

sealed class CreationIntent: UiIntent() {
    data object ClickBackButton: CreationIntent()
    data object ClickNextButton: CreationIntent()
    data class ChangeOrganizationNameTextValue(val text: String): CreationIntent()
    data object ClearOrganizationName: CreationIntent()
    data object ClearFocus: CreationIntent()
    data class ClickCategoryItem(val selectedIndex: Int): CreationIntent()
    data object ClickImageView : CreationIntent()
    data class ClickImageBottomSheetItem(val item: BottomSheetItem) : CreationIntent()
    data object HideImageBottomSheet: CreationIntent()
    data class PickImage(val uri: Uri?) : CreationIntent()
    data class ChangeEndDate(val date: LocalDate): CreationIntent()
    data class ChangePromotionTextValue(val text: String): CreationIntent()
    data object ClearPromotionCode: CreationIntent()
}