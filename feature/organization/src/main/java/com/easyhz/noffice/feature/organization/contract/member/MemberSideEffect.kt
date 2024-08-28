package com.easyhz.noffice.feature.organization.contract.member

import androidx.annotation.StringRes
import com.easyhz.noffice.core.common.base.UiSideEffect

sealed class MemberSideEffect: UiSideEffect() {
    data object NavigateToUp: MemberSideEffect()
    data class NavigateToInitiation(val url: String, val imageUrl: String): MemberSideEffect()
    data object HideBottomSheet: MemberSideEffect()
    data class ShowSnackBar(@StringRes val stringId: Int): MemberSideEffect()
}