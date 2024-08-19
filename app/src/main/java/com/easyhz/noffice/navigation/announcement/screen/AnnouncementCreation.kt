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
    data class DateTime(
        val date: String?,
        val time: String?
    )

    @Serializable
    data class Place(
        val contactType: String?,
        val title: String?,
        val url: String?
    )

    @Serializable
    data class Task(
        val taskList: List<String>? = null
    )

    @Serializable
    data class Remind(
        val remindList: List<String>? = null
    )

    @Serializable
    data object CustomRemind
}