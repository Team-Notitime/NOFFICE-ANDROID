package com.easyhz.noffice.navigation.util

import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.util.BottomMenu
import com.easyhz.noffice.navigation.home.screen.Home
import com.easyhz.noffice.navigation.organization.screen.Organization

enum class BottomMenuTabs(
    val qualifierName: String
): BottomMenu {
    HOME(qualifierName = Home::class.java.name) {
        override val iconId: Int
            get() = R.drawable.ic_home
        override val label: String
            get() = "home"
    },
    ADD(qualifierName = Home::class.java.name) { // FIXME: ADD Screen 으로 변경
        override val iconId: Int
            get() = R.drawable.ic_add
        override val label: String
            get() = "add"
    },
    ORGANIZATION(qualifierName = Organization::class.java.name) {
        override val iconId: Int
            get() = R.drawable.ic_group
        override val label: String
            get() = "organization"
    }
}