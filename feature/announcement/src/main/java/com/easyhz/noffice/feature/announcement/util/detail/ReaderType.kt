package com.easyhz.noffice.feature.announcement.util.detail

import androidx.annotation.StringRes
import com.easyhz.noffice.core.design_system.util.tab.SegmentType
import com.easyhz.noffice.core.design_system.R

enum class ReaderType(
    @StringRes val emptyStringId: Int
): SegmentType {
    READER(
        emptyStringId = R.string.announcement_detail_reader_empty
    ) {
        override val labelId: Int
            get() = R.string.announcement_detail_reader
        override val iconId: Int
            get() = R.drawable.ic_user_check
    },
    NON_READER(
        emptyStringId = R.string.announcement_detail_non_reader_empty
    ) {
        override val labelId: Int
            get() = R.string.announcement_detail_non_reader
        override val iconId: Int
            get() = R.drawable.ic_user_x
    }
}

