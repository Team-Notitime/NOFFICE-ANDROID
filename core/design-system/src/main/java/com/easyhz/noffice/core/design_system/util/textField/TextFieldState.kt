package com.easyhz.noffice.core.design_system.util.textField

internal sealed interface TextFieldState {
    data object Default : TextFieldState
    data object Active : TextFieldState
    data object Filled : TextFieldState
}

internal fun getTextFieldState(
    text: String,
    isFilled: Boolean
): TextFieldState {
    return if (text.isEmpty()) {
        TextFieldState.Default
    } else if (isFilled) {
        TextFieldState.Filled
    } else {
        TextFieldState.Active
    }
}
