package com.easyhz.noffice.core.common.util

import java.net.URLDecoder
import java.nio.charset.StandardCharsets

object UrlUtil {
    fun decode(url: String): String {
        return URLDecoder.decode(
            url,
            StandardCharsets.UTF_8.toString()
        )
    }
}