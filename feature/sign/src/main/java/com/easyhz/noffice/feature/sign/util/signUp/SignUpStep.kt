package com.easyhz.noffice.feature.sign.util.signUp

enum class SignUpStep {
    TERMS, NAME;

    fun nextStep(): SignUpStep? =
        entries.getOrNull(this.ordinal + 1)

    fun beforeStep(): SignUpStep? =
        entries.getOrNull(this.ordinal - 1)
}