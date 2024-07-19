package com.easyhz.noffice.feature.sign.util.signUp

import java.util.EnumMap

enum class SignUpStep {
    TERMS, NAME;

    fun nextStep(): SignUpStep? =
        entries.getOrNull(this.ordinal + 1)

    fun beforeStep(): SignUpStep? =
        entries.getOrNull(this.ordinal - 1)
}

fun List<SignUpStep>.toEnabledStepButton(): EnumMap<SignUpStep, Boolean> =
    EnumMap<SignUpStep, Boolean>(SignUpStep::class.java).apply {
        this@toEnabledStepButton.forEach { step ->
            this[step] = false
        }
    }