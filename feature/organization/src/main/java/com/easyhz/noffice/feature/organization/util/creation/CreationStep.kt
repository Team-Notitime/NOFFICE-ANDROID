package com.easyhz.noffice.feature.organization.util.creation

import com.easyhz.noffice.core.common.util.StepRequired

enum class CreationStep: StepRequired {
    ORGANIZATION_NAME {
        override val isRequired: Boolean
            get() = true
    }, CATEGORY {
        override val isRequired: Boolean
            get() = true
    }, IMAGE {
        override val isRequired: Boolean
            get() = false
    }, END_DATE {
        override val isRequired: Boolean
            get() = false
    }, PROMOTION {
        override val isRequired: Boolean
            get() = false
    }, SUCCESS {
        override val isRequired: Boolean
            get() = false
    };

    fun nextStep(): CreationStep? =
        entries.getOrNull(this.ordinal + 1)

    fun beforeStep(): CreationStep? =
        entries.getOrNull(this.ordinal - 1)
}