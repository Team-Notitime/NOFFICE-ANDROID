package com.easyhz.noffice.feature.organization.contract.member

import com.easyhz.noffice.core.common.base.UiSideEffect

sealed class MemberSideEffect: UiSideEffect() {
    data object NavigateToUp: MemberSideEffect()
    data object HideBottomSheet: MemberSideEffect()
}