package com.easyhz.noffice.feature.home.contract.home

import androidx.annotation.StringRes
import com.easyhz.noffice.core.common.base.UiSideEffect
import com.easyhz.noffice.core.model.organization.OrganizationSignUpInformation

sealed class HomeSideEffect: UiSideEffect() {
    data object NavigateToMyPage: HomeSideEffect()
    data class NavigateToOrganizationJoin(val organizationSignUpInformation: OrganizationSignUpInformation): HomeSideEffect()
    data class ShowSnackBar(@StringRes val stringId: Int): HomeSideEffect()
}