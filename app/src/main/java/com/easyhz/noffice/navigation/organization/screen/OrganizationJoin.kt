package com.easyhz.noffice.navigation.organization.screen

import com.easyhz.noffice.core.model.organization.OrganizationSignUpInformation
import com.easyhz.noffice.navigation.util.serializableType
import kotlinx.serialization.Serializable
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import kotlin.reflect.typeOf

@Serializable
data class OrganizationJoin(
    val organizationSignUpInformation: OrganizationSignUpInformation
) {
    companion object {
        val typeMap = mapOf(
            typeOf<OrganizationSignUpInformation>() to serializableType<OrganizationSignUpInformation>()
        )
        fun OrganizationSignUpInformation.encode(): OrganizationSignUpInformation {
            val encodeProfileUrl = URLEncoder.encode(
                this.profileImage,
                StandardCharsets.UTF_8.toString()
            )
            return this.copy(profileImage = encodeProfileUrl)
        }

        fun OrganizationSignUpInformation.decode(): OrganizationSignUpInformation {
            val decodeProfileUrl = URLDecoder.decode(
                this.profileImage,
                StandardCharsets.UTF_8.toString()
            )
            return this.copy(profileImage = decodeProfileUrl)
        }
    }
}
