package com.easyhz.noffice.feature.organization.contract.member

import com.easyhz.noffice.core.common.base.UiIntent
import com.easyhz.noffice.core.model.organization.member.MemberType

sealed class MemberIntent: UiIntent() {
    data object NavigateToUp: MemberIntent()
    data object ClickLeftButton: MemberIntent()
    data object ClickRightButton: MemberIntent()
    data object HideBottomSheet: MemberIntent()
    data object CompleteHideBottomSheet: MemberIntent()
    data class ClickAuthorityMemberType(val type: MemberType): MemberIntent()
    data object ClickAuthorityButton: MemberIntent()
}