package com.easyhz.noffice.core.design_system.util.terms

import androidx.annotation.StringRes
import com.easyhz.noffice.core.design_system.R

enum class TermsType(
    @StringRes val title: Int,
    val fileName: String,
) {
    SERVICE_OF_TERMS(
        title = R.string.service_of_terms_title,
        fileName = "service_of_terms.html"
    ),
    PRIVACY_POLICY(
        title = R.string.privacy_policy_title,
        fileName = "privacy_policy.html"
    ),
}