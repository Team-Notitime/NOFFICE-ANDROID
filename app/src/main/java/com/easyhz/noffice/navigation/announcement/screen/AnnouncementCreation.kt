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
}