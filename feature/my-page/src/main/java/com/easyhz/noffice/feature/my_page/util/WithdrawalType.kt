package com.easyhz.noffice.feature.my_page.util

import androidx.annotation.StringRes
import com.easyhz.noffice.core.design_system.R

enum class WithdrawalType(
    @StringRes val stringId: Int
) {
    ID(
        stringId = R.string.my_page_menu_withdrawal_type_id
    ),
    ACTIVITY(
        stringId = R.string.my_page_menu_withdrawal_type_activity
    ),
    INFO(
        stringId = R.string.my_page_menu_withdrawal_type_info
    )
}