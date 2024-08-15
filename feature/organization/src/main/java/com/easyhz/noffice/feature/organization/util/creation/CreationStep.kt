package com.easyhz.noffice.feature.organization.util.creation

import androidx.annotation.StringRes
import com.easyhz.noffice.core.common.util.StepRequired
import com.easyhz.noffice.core.design_system.R

enum class CreationStep(
    @StringRes val titleId: Int
): StepRequired {
    ORGANIZATION_NAME(
       titleId = R.string.organization_creation_name_title
    ) {
        override val isRequired: Boolean
            get() = true
    }, CATEGORY(
        titleId = R.string.organization_creation_category_title
    ) {
        override val isRequired: Boolean
            get() = true
    }, IMAGE(
        titleId = R.string.organization_creation_image_title
    ) {
        override val isRequired: Boolean
            get() = false
    }, END_DATE(
        titleId = R.string.organization_creation_end_date_title
    ) {
        override val isRequired: Boolean
            get() = false
    }, PROMOTION(
        titleId = R.string.organization_creation_promotion_title
    ) {
        override val isRequired: Boolean
            get() = false
    };

    fun nextStep(): CreationStep? =
        entries.getOrNull(this.ordinal + 1)

    fun beforeStep(): CreationStep? =
        entries.getOrNull(this.ordinal - 1)
}