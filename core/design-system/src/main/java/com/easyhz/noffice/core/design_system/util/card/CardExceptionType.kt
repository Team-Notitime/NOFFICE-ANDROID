package com.easyhz.noffice.core.design_system.util.card

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.easyhz.noffice.core.design_system.R

enum class CardExceptionType(
    @DrawableRes val resId: Int,
    @StringRes val titleStringId: Int,
    @StringRes val subTitleStringId: Int
) {
    ACCEPT_WAIT(
        resId = R.drawable.ic_loading,
        titleStringId = R.string.card_exception_accept_wait_title,
        subTitleStringId = R.string.card_exception_accept_wait_sub_title,
    ), NO_RESULT(
        resId = R.drawable.ic_no_result,
        titleStringId = R.string.card_exception_no_result_title,
        subTitleStringId = R.string.card_exception_no_result_sub_title,
    )
}