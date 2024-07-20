package com.easyhz.noffice.core.common.util

import java.util.EnumMap

data class Step<T>(
    val currentStep: T,
    val previousStep: T?
)
interface StepRequired {
    val isRequired: Boolean
}
inline fun <reified T> List<T>.toEnabledStepButton(): EnumMap<T, Boolean> where T: Enum<T>, T: StepRequired =
    EnumMap<T, Boolean>(T::class.java).apply {
        this@toEnabledStepButton.forEach { step ->
            this[step] = !step.isRequired
        }
    }

fun <T> EnumMap<T, Boolean>.updateStepButton(key: T, isEnabled: Boolean): EnumMap<T, Boolean>
where T: Enum<T> {
    this[key] = isEnabled
    return this
}