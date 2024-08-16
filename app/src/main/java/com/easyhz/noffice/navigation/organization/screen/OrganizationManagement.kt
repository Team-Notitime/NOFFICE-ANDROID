package com.easyhz.noffice.navigation.organization.screen

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.easyhz.noffice.core.model.organization.OrganizationInformation
import com.easyhz.noffice.core.model.organization.member.MemberType
import com.easyhz.noffice.navigation.util.serializableType
import kotlinx.serialization.Serializable
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
    }
}