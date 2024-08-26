package com.easyhz.noffice.navigation.my_page.screen

import com.easyhz.noffice.core.design_system.util.terms.TermsType
import com.easyhz.noffice.navigation.util.serializableType
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@Serializable
data class Terms(
    val termsType: TermsType
) {
    companion object {
        val typeMap = mapOf(
            typeOf<TermsType>() to serializableType<TermsType>()
        )
    }
}
