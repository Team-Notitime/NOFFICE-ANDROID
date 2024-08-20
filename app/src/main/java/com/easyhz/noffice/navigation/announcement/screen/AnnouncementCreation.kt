package com.easyhz.noffice.navigation.announcement.screen

import android.os.Parcelable
import com.easyhz.noffice.core.model.announcement.param.AnnouncementParam
import com.easyhz.noffice.navigation.util.serializableType
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import kotlin.reflect.typeOf

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
        val remindList: List<String>? = null,
        val isSelectedDateTime: Boolean = false
    )

    @Serializable
    data object CustomRemind

    @Serializable
    data class Promotion(
        val announcementParam: AnnouncementParam,
    ) {
        companion object {
            val typeMap = mapOf(
                typeOf<AnnouncementParam>() to serializableType<AnnouncementParam>(),
            )

            fun AnnouncementParam.encode(): AnnouncementParam {
                val encodePlaceLinkUrl = URLEncoder.encode(
                    this.placeLinkUrl,
                    StandardCharsets.UTF_8.toString()
                )
                return this.copy(placeLinkUrl = encodePlaceLinkUrl)
            }

            fun AnnouncementParam.decode(): AnnouncementParam {
                val decodePlaceLinkUrl = URLDecoder.decode(
                    this.placeLinkUrl,
                    StandardCharsets.UTF_8.toString()
                )
                return this.copy(placeLinkUrl = decodePlaceLinkUrl)
            }
        }
    }

}