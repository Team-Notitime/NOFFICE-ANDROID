package com.easyhz.noffice.feature.announcement.screen.creation.place

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.feature.announcement.contract.creation.place.ContactType
import com.easyhz.noffice.feature.announcement.contract.creation.place.PlaceIntent
import com.easyhz.noffice.feature.announcement.contract.creation.place.PlaceSideEffect
import com.easyhz.noffice.feature.announcement.contract.creation.place.PlaceState
import com.easyhz.noffice.feature.announcement.util.creation.OptionData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlaceViewModel @Inject constructor(

): BaseViewModel<PlaceState, PlaceIntent, PlaceSideEffect>(
    initialState = PlaceState.init()
) {
    private var hasFocus by mutableStateOf(false)

    override fun handleIntent(intent: PlaceIntent) {
        when(intent) {
            is PlaceIntent.InitScreen -> { initScreen(intent.contactType, intent.title, intent.url) }
            is PlaceIntent.ClickBackButton -> { onClickBackButton() }
            is PlaceIntent.ClickSaveButton -> { onClickSaveButton() }
            is PlaceIntent.SelectedContactType -> { onSelectedContactType(intent.contactType) }
            is PlaceIntent.ChangePlaceUrlTextValue -> { onChangePlaceUrlTextValue(intent.newText) }
            is PlaceIntent.ClearPlaceUrlTextValue -> { onClearPlaceUrlTextValue() }
            is PlaceIntent.ChangePlaceTitleTextValue -> { onChangePlaceTitleTextValue(intent.newText) }
            is PlaceIntent.ClearPlaceTitleTextValue -> { onClearPlaceTitleTextValue() }
            is PlaceIntent.ChangedFocus -> { onChangeFocus(intent.hasFocus) }
        }
    }

    private fun initScreen(contactType: String?, title: String?, url: String?) {
        contactType?.let {
            when(val type = ContactType.valueOf(it)) {
                ContactType.CONTACT -> {
                    reduce { updateContactState(contactType = type, url = url) }
                }
                ContactType.NONE_CONTACT -> {
                    reduce { updateContactState(contactType = type, title = title, url = url) }
                }
            }
        }
    }

    private fun onClickBackButton() {
        postSideEffect { PlaceSideEffect.NavigateToUp }
    }

    private fun onClickSaveButton() {
        postSideEffect {
            PlaceSideEffect.NavigateToNext(
                OptionData.Place(currentState.contactState, currentState.contactState.url.isNotBlank())
            )
        }
    }

    private fun onSelectedContactType(contactType: ContactType) {
        reduce { updateContactState(contactType = contactType) }
        if (hasFocus) {
            postSideEffect { PlaceSideEffect.ClearFocus }
        }
    }

    private fun onChangePlaceUrlTextValue(newText: String) {
        reduce { updateContactState(url = newText) }
    }

    private fun onClearPlaceUrlTextValue() {
        reduce { updateContactState(url = "") }
    }

    private fun onChangePlaceTitleTextValue(newText: String) {
        reduce { updateContactState(title = newText) }
    }

    private fun onClearPlaceTitleTextValue() {
        reduce { updateContactState(title = "") }
    }

    private fun onChangeFocus(hasFocus: Boolean) {
        this.hasFocus = hasFocus
    }

}