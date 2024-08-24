package com.easyhz.noffice.core.common.manager

object DeepLinkManager {

    var organizationIdToJoin: Int = -1
        private set


    fun setOrganizationIdToJoin(value: Int) {
        organizationIdToJoin = value
    }

    fun clearOrganizationIdToJoin() {
        organizationIdToJoin = -1
    }
}