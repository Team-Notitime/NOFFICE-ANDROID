package com.easyhz.noffice.feature.sign.util.signUp

import com.easyhz.noffice.core.common.util.StepRequired

enum class SignUpStep: StepRequired {
    TERMS {
        override val isRequired: Boolean
            get() = true
    }, NAME {
        override val isRequired: Boolean
            get() = true
    };

    fun nextStep(): SignUpStep? =
        entries.getOrNull(this.ordinal + 1)

    fun beforeStep(): SignUpStep? =
        entries.getOrNull(this.ordinal - 1)
}