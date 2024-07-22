package com.easyhz.noffice.feature.sign.util.login

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.theme.Grey50
import com.easyhz.noffice.core.design_system.theme.Grey800

enum class SocialLoginType(
    @StringRes val stringId: Int,
    @DrawableRes val iconId: Int,
    val containerColor: Color,
    val contentColor: Color,
) {
    GOOGLE(
        stringId = R.string.social_login_google,
        iconId = R.drawable.ic_logo_google,
        containerColor = Grey50,
        contentColor = Grey800,
    );

    interface OnItemClickListener {
        fun onClickGoogle()
    }

}