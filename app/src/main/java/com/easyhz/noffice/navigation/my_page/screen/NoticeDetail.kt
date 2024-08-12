package com.easyhz.noffice.navigation.my_page.screen

import com.easyhz.noffice.core.model.notice.Notice
import com.easyhz.noffice.navigation.util.serializableType
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@Serializable
data class NoticeDetail(
    val notice: Notice
) {
    companion object {
        val typeMap = mapOf(
            typeOf<Notice>() to serializableType<Notice>()
        )
    }
}
