package com.easyhz.noffice.core.design_system.util.card

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.easyhz.noffice.core.design_system.R

enum class CardExceptionType(
    @DrawableRes val resId: Int,
    @StringRes val stringId: Int
) {
    ACCEPT_WAIT(
        resId = R.drawable.ic_loading,
        stringId = R.string.card_exception_accept_wait
    ), NO_RESULT(
        resId = R.drawable.ic_no_result,
        stringId = R.string.card_exception_no_result
    )
}