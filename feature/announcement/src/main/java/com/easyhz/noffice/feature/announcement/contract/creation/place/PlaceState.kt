package com.easyhz.noffice.feature.announcement.contract.creation.place

import com.easyhz.noffice.core.common.base.UiState
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.util.tab.SegmentType
import com.easyhz.noffice.core.model.announcement.creation.place.OpenGraph

data class PlaceState(
    val contactState: ContactState,
    val openGraph: OpenGraph,
    val isLoading: Boolean,
    val isVisible: Boolean,
) : UiState() {
    companion object {
        fun init() = PlaceState(
            contactState = ContactState(),
            openGraph = OpenGraph(
                title = null,
                description = null,
                imageUrl = null,
                url = null
            ),
            isLoading = false,
            isVisible = false
        )
    }

    fun PlaceState.updateContactState(
        contactType: ContactType? = null,
        title: String? = null,
        url: String? = null
    ): PlaceState = this.copy(
        contactState = this.contactState.copy(
            contactType = contactType ?: this.contactState.contactType,
            title = title ?: this.contactState.title,
            url = url ?: this.contactState.url
        )
    )
}


data class ContactState(
    val contactType: ContactType = ContactType.CONTACT,
    val title: String = "",
    val url: String = ""
)

enum class ContactType : SegmentType {
    CONTACT {
        override val labelId: Int
            get() = R.string.announcement_creation_option_place_type_contact
    },
    NONE_CONTACT {
        override val labelId: Int
            get() = R.string.announcement_creation_option_place_type_none_contact
    }
}