package com.easyhz.noffice.core.common.util

data class Step<T>(
    val currentStep: T,
    val previousStep: T?
)
