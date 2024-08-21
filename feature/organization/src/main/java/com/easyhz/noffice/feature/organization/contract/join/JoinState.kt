package com.easyhz.noffice.feature.organization.contract.join

import com.easyhz.noffice.core.common.base.UiState
import com.easyhz.noffice.core.model.organization.OrganizationSignUpInformation

data class JoinState(
    val organizationSignUpInformation: OrganizationSignUpInformation,
): UiState() {
    companion object {
        fun init() = JoinState(
            organizationSignUpInformation = OrganizationSignUpInformation(
                organizationId = -1,
                organizationName = "",
                profileImage = ""
            ),
        )
    }
}
