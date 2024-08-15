package com.easyhz.noffice.core.design_system.util.exception

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.easyhz.noffice.core.design_system.R

enum class ExceptionType(
    @DrawableRes val resId: Int,
    @StringRes val titleStringId: Int,
    @StringRes val subTitleStringId: Int
) {
    NO_ORGANIZATION(
        resId = R.drawable.ic_empty_no_group,
        titleStringId = R.string.exception_no_organization_title,
        subTitleStringId = R.string.exception_no_organization_sub_title,
    ), NO_TASK(
        resId = R.drawable.ic_empty_no_task,
        titleStringId = R.string.exception_no_task_title,
        subTitleStringId = R.string.exception_no_task_sub_title,
    )
}