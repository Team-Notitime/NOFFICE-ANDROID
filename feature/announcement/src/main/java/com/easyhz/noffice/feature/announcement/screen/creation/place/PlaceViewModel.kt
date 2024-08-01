package com.easyhz.noffice.feature.announcement.screen.creation.place

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.core.model.announcement.creation.place.OpenGraph
import com.easyhz.noffice.domain.announcement.usecase.creation.place.FetchOpenGraphDataUseCase
import com.easyhz.noffice.feature.announcement.contract.creation.place.ContactType
import com.easyhz.noffice.feature.announcement.contract.creation.place.PlaceIntent
import com.easyhz.noffice.feature.announcement.contract.creation.place.PlaceSideEffect
import com.easyhz.noffice.feature.announcement.contract.creation.place.PlaceState
import com.easyhz.noffice.feature.announcement.util.creation.OptionData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaceViewModel @Inject constructor(
    private val fetchOpenGraphDataUseCase: FetchOpenGraphDataUseCase
) : BaseViewModel<PlaceState, PlaceIntent, PlaceSideEffect>(
    initialState = PlaceState.init()
) {
    private var hasFocus by mutableStateOf(false)
    private var debounceJob: Job? = null
    override fun handleIntent(intent: PlaceIntent) {
        when (intent) {
            is PlaceIntent.InitScreen -> {
                initScreen(intent.contactType, intent.title, intent.url)
            }

            is PlaceIntent.ClickBackButton -> {
                onClickBackButton()
            }

            is PlaceIntent.ClickSaveButton -> {
                onClickSaveButton()
            }

            is PlaceIntent.SelectedContactType -> {
                onSelectedContactType(intent.contactType)
            }

            is PlaceIntent.ChangePlaceUrlTextValue -> {
                onChangePlaceUrlTextValue(intent.newText)
            }

            is PlaceIntent.ClearPlaceUrlTextValue -> {
                onClearPlaceUrlTextValue()
            }

            is PlaceIntent.ChangePlaceTitleTextValue -> {
                onChangePlaceTitleTextValue(intent.newText)
            }

            is PlaceIntent.ClearPlaceTitleTextValue -> {
                onClearPlaceTitleTextValue()
            }

            is PlaceIntent.ChangedFocus -> {
                onChangeFocus(intent.hasFocus)
            }
            is PlaceIntent.ReadyForOpenGraph -> {
                onReadyForOpenGraph()
            }
        }
    }

    private fun initScreen(contactType: String?, title: String?, url: String?) {
        contactType?.let {
            when (val type = ContactType.valueOf(it)) {
                ContactType.CONTACT -> {
                    reduce { updateContactState(contactType = type, title = title, url = url) }
                }

                ContactType.NONE_CONTACT -> {
                    reduce { updateContactState(contactType = type, title = title, url = url) }
                }
            }
            if (url != null) {
                onChangePlaceUrlTextValue(url)
            }
        }
    }

    private fun onClickBackButton() {
        postSideEffect { PlaceSideEffect.NavigateToUp }
    }

    private fun onClickSaveButton() {
        postSideEffect {
            PlaceSideEffect.NavigateToNext(
                OptionData.Place(
                    currentState.contactState,
                    currentState.contactState.url.isNotBlank()
                )
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
        reduce { updateContactState(url = newText.replace("\n", " ")) }
        setVisibilityOpenGraph()
        debounceJob?.cancel()
        debounceJob = fetchOpenGraphData()
    }

    private fun onClearPlaceUrlTextValue() {
        reduce { updateContactState(url = "") }
        setVisibilityOpenGraph()
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

    private fun setVisibilityOpenGraph() {
        val isUrlBlank = currentState.contactState.url.isBlank()

        if (currentState.isVisible && isUrlBlank) {
            reduce { copy(isVisible = false) }
        } else if (!currentState.isVisible && !isUrlBlank) {
            reduce { copy(isVisible = true, isLoading = true) }
        } else if(currentState.isVisible && !currentState.isLoading) {
            reduce { copy(isLoading = true) }
        }
    }

    private fun fetchOpenGraphData() = viewModelScope.launch {
        delay(1500)
        val currentUrl = currentState.contactState.url
        if (currentUrl.isBlank()) return@launch
        val (isExtract , url) = validateUrl(currentUrl)
        if (url.isNullOrBlank()) return@launch
        val openGraph = fetchOpenGraphDataUseCase.invoke(url).getOrElse {
            OpenGraph(
                title = "",
                description = "",
                imageUrl = "",
                url = currentUrl
            )
        }
        val newOpenGraph = openGraph.takeIf { !isExtract } ?: openGraph.copy(url = currentUrl)
        reduce { copy(isLoading = false, openGraph = newOpenGraph) }
        setAutoFillText()
        postSideEffect { PlaceSideEffect.ClearFocus }
    }

    private fun onReadyForOpenGraph() {
        postSideEffect { PlaceSideEffect.ClearFocus }
    }

    private fun validateUrl(url: String): Pair<Boolean, String?> {
        return if (url.isBlank()) {
            false to null
        } else if(!extractUrl(url).isNullOrBlank()) {
            false to extractUrl(url)
        } else if (!url.startsWith("http://") && !url.startsWith("https://")) {
            false to "https://$url"
        } else {
            false to url
        }
    }

    private fun extractUrl(text: String): String? {
        val urlRegex = "https?://[\\w\\-]+(\\.[\\w\\-]+)+[/\\w\\-.,@?^=%&:;#~+]*[\\w\\-@?^=%&/~+#]?"
        val regex = Regex(urlRegex)
        return regex.find(text)?.value
    }

    private fun setAutoFillText() {
        if (currentState.contactState.title.isBlank()) {
            println(">> ${currentState.openGraph}")
            reduce { updateContactState(title = openGraph.title, url = openGraph.url) }
        }
    }
}