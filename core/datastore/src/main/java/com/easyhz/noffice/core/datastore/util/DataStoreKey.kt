package com.easyhz.noffice.core.datastore.util

internal enum class AuthKey(
    val key: String
) {
    ACCESS_TOKEN(
        key = "ACCESS_TOKEN"
    ), REFRESH_TOKEN(
        key = "REFRESH_TOKEN"
    ), AUTH_PROVIDER(
        key = "PROVIDER"
    )
}