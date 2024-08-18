package com.easyhz.noffice.build_logic.convention

enum class NofficeBuildType(val applicationSuffix: String? = null) {
    DEBUG(".debug"),
    RELEASE(".release")
}