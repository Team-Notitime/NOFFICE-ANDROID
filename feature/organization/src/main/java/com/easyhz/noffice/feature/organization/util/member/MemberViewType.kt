package com.easyhz.noffice.feature.organization.util.member

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.easyhz.noffice.core.design_system.theme.Grey100
import com.easyhz.noffice.core.design_system.theme.Grey600
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.theme.Green100
import com.easyhz.noffice.core.design_system.theme.Green500
import com.easyhz.noffice.core.design_system.theme.Green700
import com.easyhz.noffice.core.design_system.theme.Red
import com.easyhz.noffice.core.design_system.theme.Red100
import com.easyhz.noffice.core.design_system.theme.White

enum class MemberViewType(
    @StringRes val title: Int,
    val left: ButtonStyle,
    val right: ButtonStyle,
) {
    MANAGEMENT(
        title = R.string.organization_management_member_management_title,
        left = ButtonStyle(
            title = R.string.organization_management_member_management_left_button
        ),
        right = ButtonStyle(
            title = R.string.organization_management_member_management_right_button,
            containerColor = Green500,
            contentColor = White
        )
    ), EDIT(
        title = R.string.organization_management_member_edit_title,
        left = ButtonStyle(
            title = R.string.organization_management_member_edit_left_button,
            containerColor = Red100,
            contentColor = Red
        ),
        right = ButtonStyle(
            title = R.string.organization_management_member_edit_right_button,
            containerColor = Green100,
            contentColor = Green700
        )
    );

    interface ClickListener {
        fun onClickLeftButton()
        fun onClickRightButton()
    }
}

data class ButtonStyle(
    @StringRes val title: Int,
    val containerColor: Color = Grey100,
    val contentColor: Color = Grey600
)