package com.easyhz.noffice.navigation.announcement.screen

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
internal object AnnouncementCreation: Parcelable {
    @Serializable
    data object NofficeSelection

    @Serializable
    data object Content

    @Serializable
    data object DateTime

    @Serializable
    data class Place(
        val contactType: String?,
        val title: String?,
        val url: String?
    )

    @Serializable
    data object Task

    @Serializable
    data object Remind
}