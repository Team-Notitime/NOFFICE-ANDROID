package com.easyhz.noffice.feature.organization.contract.member

import com.easyhz.noffice.core.common.base.UiSideEffect

sealed class MemberSideEffect: UiSideEffect() {
    data object NavigateToUp: MemberSideEffect()
    data class NavigateToInitiation(val url: String, val imageUrl: String): MemberSideEffect()
    data object HideBottomSheet: MemberSideEffect()
}