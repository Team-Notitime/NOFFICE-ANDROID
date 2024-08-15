package com.easyhz.noffice.feature.announcement.contract.creation.place

import com.easyhz.noffice.core.common.base.UiIntent

sealed class PlaceIntent: UiIntent() {
    data class InitScreen(val contactType: String?, val title: String?, val url: String?): PlaceIntent()
    data object ClickBackButton: PlaceIntent()
    data object ClickSaveButton: PlaceIntent()
    data class SelectedContactType(val contactType: ContactType): PlaceIntent()
    data class ChangePlaceUrlTextValue(val newText: String): PlaceIntent()
    data object ClearPlaceUrlTextValue: PlaceIntent()
    data class ChangePlaceTitleTextValue(val newText: String): PlaceIntent()
    data object ClearPlaceTitleTextValue: PlaceIntent()
    data class ChangedFocus(val hasFocus: Boolean): PlaceIntent()
    data object ReadyForOpenGraph: PlaceIntent()
}