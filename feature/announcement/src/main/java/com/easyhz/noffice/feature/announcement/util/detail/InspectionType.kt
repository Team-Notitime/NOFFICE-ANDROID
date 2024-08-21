package com.easyhz.noffice.feature.announcement.util.detail

import com.easyhz.noffice.core.design_system.util.tab.SegmentType
import com.easyhz.noffice.core.design_system.R

enum class InspectionType: SegmentType {
    INSPECTION {
        override val labelId: Int
            get() = R.string.announcement_detail_inspection
        override val iconId: Int
            get() = R.drawable.ic_user_check
    },
    NON_INSPECTION {
        override val labelId: Int
            get() = R.string.announcement_detail_non_inspection
        override val iconId: Int
            get() = R.drawable.ic_user_x
    }
}

