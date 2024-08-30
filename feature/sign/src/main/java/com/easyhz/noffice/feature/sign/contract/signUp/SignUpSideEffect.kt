package com.easyhz.noffice.feature.sign.contract.signUp

import androidx.annotation.StringRes
import com.easyhz.noffice.core.common.base.UiSideEffect

sealed class SignUpSideEffect: UiSideEffect() {
    data object ClearFocus: SignUpSideEffect()
    data object NavigateToHome: SignUpSideEffect()
    data object HideTermsBottomSheet: SignUpSideEffect()
    data object NavigateToUp: SignUpSideEffect()
    data class ShowSnackBar(@StringRes val stringId: Int): SignUpSideEffect()
}