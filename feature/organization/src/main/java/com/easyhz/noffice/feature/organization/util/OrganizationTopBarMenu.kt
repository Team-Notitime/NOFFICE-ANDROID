package com.easyhz.noffice.feature.organization.util

import com.easyhz.noffice.core.design_system.util.topBar.TopBarMenu

enum class OrganizationTopBarMenu : TopBarMenu {
    ORGANIZATION {
        override val label: String
            get() = "그룹"
    }
}