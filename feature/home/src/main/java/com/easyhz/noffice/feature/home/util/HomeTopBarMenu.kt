package com.easyhz.noffice.feature.home.util

import com.easyhz.noffice.core.design_system.util.topBar.TopBarMenu

enum class HomeTopBarMenu : TopBarMenu {
    NOTICE {
        override val label: String
            get() = "노티"
    }, TASK {
        override val label: String
            get() = "투두"
    }
}