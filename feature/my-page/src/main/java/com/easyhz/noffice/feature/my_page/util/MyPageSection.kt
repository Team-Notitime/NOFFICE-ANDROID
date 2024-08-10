package com.easyhz.noffice.feature.my_page.util

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.theme.Grey800
import com.easyhz.noffice.core.design_system.theme.Red


enum class MyPageSection(
    @StringRes
    override val titleId: Int,
    val items: List<MyPageMenu>
) : Menu {
    GUIDE(
        titleId = R.string.my_page_menu_title_guide,
        items = listOf(
            MyPageMenu.VERSION,
            MyPageMenu.INQUIRY,
            MyPageMenu.NOTICE,
            MyPageMenu.TERMS_OF_SERVICE,
            MyPageMenu.PRIVACY_POLICY
        )
    ),
    SETTING(
        titleId = R.string.my_page_menu_title_setting,
        items = listOf(MyPageMenu.NOTIFICATION)
    ),
    ETC(
        titleId = R.string.my_page_menu_title_etc,
        items = listOf(
            MyPageMenu.CONSENT_TO_INFORMATION,
            MyPageMenu.WITHDRAWAL,
            MyPageMenu.SIGN_OUT
        )
    )
}

enum class MyPageMenu(
    @StringRes
    override val titleId: Int,
    val type: MenuType = MenuType.MORE,
    val textColor: Color = Grey800
) : Menu {
    VERSION(
        titleId = R.string.my_page_menu_version,
        type = MenuType.VERSION_TEXT
    ),
    INQUIRY(
        titleId = R.string.my_page_menu_inquiry,
    ),
    NOTICE(
        titleId = R.string.my_page_menu_notice,
    ),
    TERMS_OF_SERVICE(
        titleId = R.string.my_page_menu_terms_of_service
    ),
    PRIVACY_POLICY(
        titleId = R.string.my_page_menu_privacy_policy
    ),
    NOTIFICATION(
        titleId = R.string.my_page_menu_notification,
        type = MenuType.TOGGLE
    ),
    CONSENT_TO_INFORMATION(
        titleId = R.string.my_page_menu_consent_to_information
    ),
    WITHDRAWAL(
        titleId = R.string.my_page_menu_delete_account,
        textColor = Red
    ),
    SIGN_OUT(
        titleId = R.string.my_page_menu_sign_out,
        textColor = Red
    )
}

enum class MenuType {
    MORE, TOGGLE, VERSION_TEXT;

    fun isClickable() : Boolean = this != VERSION_TEXT
}

internal interface Menu {
    val titleId: Int
}


