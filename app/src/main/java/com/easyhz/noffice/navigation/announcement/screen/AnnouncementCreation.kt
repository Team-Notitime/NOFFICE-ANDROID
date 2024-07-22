package com.easyhz.noffice.navigation.announcement.screen

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
object AnnouncementCreation: Parcelable {
    @Serializable
    data object NofficeSelection

    @Serializable
    data object Content

    @Serializable
    data object DateTime

    @Serializable
    data object Place

    @Serializable
    data object Task

    @Serializable
    data object Remind
}