package com.easyhz.noffice.feature.sign.util.signUp

import androidx.annotation.StringRes
import com.easyhz.noffice.core.design_system.R
import java.util.EnumMap

enum class Terms(
    @StringRes val stringId: Int,
    val isRequired: Boolean,
    val hasDetail: Boolean,
) {
    AGE(
        stringId = R.string.sign_up_terms_age,
        isRequired = true,
        hasDetail = false
    ),
    SERVICE_OF_TERMS(
        stringId = R.string.sign_up_terms_service_of_terms,
        isRequired = true,
        hasDetail = true
    ),
    PRIVACY_POLICY(
        stringId = R.string.sign_up_terms_privacy_policy,
        isRequired = true,
        hasDetail = true
    ),
    MARKETING(
        stringId = R.string.sign_up_terms_marketing,
        isRequired = false,
        hasDetail = true
    )
}
fun List<Terms>.toTermsMap(): EnumMap<Terms, Boolean> =
    EnumMap<Terms, Boolean>(Terms::class.java).apply {
        this@toTermsMap.forEach { terms ->
            this[terms] = false
        }
    }