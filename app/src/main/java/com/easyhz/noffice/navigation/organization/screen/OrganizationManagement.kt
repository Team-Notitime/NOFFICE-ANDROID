package com.easyhz.noffice.navigation.organization.screen

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.easyhz.noffice.core.model.organization.OrganizationInformation
import com.easyhz.noffice.navigation.util.serializableType
import kotlinx.serialization.Serializable
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import kotlin.reflect.typeOf

@Serializable
data class OrganizationManagement(
    val organizationInformation: OrganizationInformation,
) {
    companion object {
        val typeMap = mapOf(
            typeOf<OrganizationInformation>() to serializableType<OrganizationInformation>(),
        )

        fun from(savedStateHandle: SavedStateHandle) =
            savedStateHandle.toRoute<OrganizationManagement>(typeMap)

        fun OrganizationInformation.encode(): OrganizationInformation {
            val encodeProfileUrl = URLEncoder.encode(
                this.profileImageUrl,
                StandardCharsets.UTF_8.toString()
            )
            return this.copy(profileImageUrl = encodeProfileUrl)
        }

        fun OrganizationInformation.decode(): OrganizationInformation {
            val decodeProfileUrl = URLDecoder.decode(
                this.profileImageUrl,
                StandardCharsets.UTF_8.toString()
            )
            return this.copy(profileImageUrl = decodeProfileUrl)
        }
    }
}